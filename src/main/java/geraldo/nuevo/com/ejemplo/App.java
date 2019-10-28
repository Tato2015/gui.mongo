package geraldo.nuevo.com.ejemplo;

import java.util.Properties;

import org.apache.kafka.common.serialization.StringSerializer;

import geraldo.nuevo.com.ejemplo.kafka.KafkaMessageProducer;
import geraldo.nuevo.com.ejemplo.kafka.KafkaProducerException;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) throws KafkaProducerException {
		System.out.println("Hello World!");
		
		
		Properties props = new Properties();
	    props.setProperty("bootstrap.servers", "localhost:9092");
	    props.setProperty("key.serializer", StringSerializer.class.getName());
	    props.setProperty("value.serializer", StringSerializer.class.getName());
	    props.setProperty("acks", "1");
	    props.setProperty("retries", "3");
	    KafkaMessageProducer producer = new KafkaMessageProducer(props);
	    producer.connect();
	    producer.sendMessage("topic-basic-test", "geek-key", "Mensaje 2 para los CONSUMERS enviado desde java");
	    producer.disconnect();
		System.out.println("Mensaje enviado");
	}
}
