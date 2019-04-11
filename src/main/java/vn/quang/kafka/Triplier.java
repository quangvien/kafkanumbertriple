package vn.quang.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.kafka.annotation.KafkaListener;

import vn.quang.entity.TheNumber;

/**
 * @author Quang N. Vien {@literal <vienngocquang@gmail.com>}
 */
public class Triplier {

	private static final Logger logger = LogManager.getLogger(Triplier.class);

	@KafkaListener(topics = "${topic}", groupId = "${groupid.consumers}", containerFactory = "kafkaListenerContainerFactoryBatch")
	public void process(ConsumerRecord<String, TheNumber> record) {
		logger.info("Partition: {}, Key: {}, Value: {}", record.partition(), record.key(), record.value().getValue()*3);
	}
}