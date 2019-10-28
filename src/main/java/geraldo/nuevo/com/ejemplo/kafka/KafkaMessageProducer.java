package geraldo.nuevo.com.ejemplo.kafka;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import geraldo.nuevo.com.ejemplo.enums.KafkaErrors;

public class KafkaMessageProducer {

	private Properties properties;
    private Producer producer;
    
    public KafkaMessageProducer(Properties properties) {
        this.properties = properties;
    }
    
    @SuppressWarnings("rawtypes")
	public void connect() {
        producer = new KafkaProducer(properties);
    }
	
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public void sendMessage(String topic, String key, String value) throws KafkaProducerException {
        if (producer != null) {
            producer.send(new ProducerRecord(topic, key, value));
        } else {
            throw new KafkaProducerException(KafkaErrors.PRODUCER_NOT_CONNECTED.getMessage());
        }
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public void sendMessage(String topic, String value) throws KafkaProducerException {
        if (producer != null) {
            producer.send(new ProducerRecord(topic, value));
        } else {
            throw new KafkaProducerException(KafkaErrors.PRODUCER_NOT_CONNECTED.getMessage());
        }
    }
    
    public void disconnect() {
        producer.close();
    }
}


