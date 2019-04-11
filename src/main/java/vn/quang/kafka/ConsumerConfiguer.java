package vn.quang.kafka;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.AsyncListenableTaskExecutor;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ContainerProperties.AckMode;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import vn.quang.entity.TheNumber;

/**
 * @author Quang N. Vien {@literal <vienngocquang@gmail.com>}
 */
@Configuration
@EnableKafka
public class ConsumerConfiguer {

	@Value("${servers}")
	private String brokers;
	
	@Value("${groupid.consumers}")
	private String groupId;

	@Value("${num.consumers}")
	private String numConsumersBatch;
	
	@Value("${enable.auto.commit}")
	private String enableAutoCommit;
	
	@Value("${auto.offset.reset}")
	private String autoOffsetReset;
	
	@Value("${poll.timeout}")
	private String pollTimeout;
	
	@Value("${request.timeout}")
	private String requestTimeout;
	
	@Value("${heartbeat.interval}")
	private String heartbeatInteval; 

	@Value("${session.timeout}")
	private String sessionTimeout;
	
	@Value("${max.poll.records}")
	private String maxPollRecords;

	@Value("${max.poll.interval}")
	private String maxPollInterval;

	@Bean
	public Map<String, Object> consumerConfigs() {
		Map<String, Object> props = new HashMap<>();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, brokers);
		props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
		props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, Boolean.valueOf(enableAutoCommit));
		props.put(ConsumerConfig.REQUEST_TIMEOUT_MS_CONFIG, Integer.valueOf(requestTimeout));
		props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, Integer.valueOf(sessionTimeout));
		props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, autoOffsetReset);
		props.put(ConsumerConfig.HEARTBEAT_INTERVAL_MS_CONFIG, Integer.valueOf(heartbeatInteval));
		props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, Integer.valueOf(maxPollRecords));
		props.put(ConsumerConfig.MAX_POLL_INTERVAL_MS_CONFIG, Integer.valueOf(maxPollInterval));
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
		
		return props;
	}
			
	@Bean
	public ConsumerFactory<String, TheNumber> consumerFactory1() {
		return new DefaultKafkaConsumerFactory<>(consumerConfigs(), new StringDeserializer(), new JsonDeserializer<>(TheNumber.class));
	}
	
	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, TheNumber> kafkaListenerContainerFactoryBatch() {
		ConcurrentKafkaListenerContainerFactory<String, TheNumber> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(consumerFactory1());
		factory.setConcurrency(Integer.valueOf(this.numConsumersBatch));
		factory.getContainerProperties().setConsumerTaskExecutor(execC());
		factory.getContainerProperties().setPollTimeout(Long.valueOf(this.pollTimeout));
		factory.getContainerProperties().setAckOnError(false);
		factory.getContainerProperties().setAckMode(AckMode.RECORD);
		
		return factory;
	}
	
	@Bean
    public AsyncListenableTaskExecutor execC() {
        ThreadPoolTaskExecutor tpte = new ThreadPoolTaskExecutor();
        tpte.setCorePoolSize(Integer.valueOf(this.numConsumersBatch));
        return tpte;
    }
	
	@Bean
	public ConsumerFactory<?, ?> consumerFactory() {
		return null;
	}
		
	@Bean
	public Triplier listerner() {
		return new Triplier();
	}
}