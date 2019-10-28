package geraldo.kafka;

import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import com.google.gson.Gson;
import com.mongodb.DBCursor;

import geraldo.kafka.constants.IKafkaConstants;
import geraldo.kafka.consumer.ConsumerCreator;
import geraldo.kafka.pojo.ObjectoPojo;
import geraldo.kafka.producer.ProducerCreator;
import geraldo.mongoDB.Connection;

public class App {


	static Connection conexion = new Connection();
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		runProducer();
		runConsumer();
	}

	static void runConsumer() {
		Consumer<Long, String> consumer = ConsumerCreator.createConsumer();

		int noMessageToFetch = 0;

		while (true) {
			final ConsumerRecords<Long, String> consumerRecords = consumer.poll(2000);
			if (consumerRecords.count() == 0) {
				noMessageToFetch++;
				if (noMessageToFetch > IKafkaConstants.MAX_NO_MESSAGE_FOUND_COUNT)
					break;
				else
					continue;
			}

			consumerRecords.forEach(record -> {
				System.out.println("Record Key " + record.key());
				System.out.println("Record value " + record.value());
				System.out.println("Record partition " + record.partition());
				System.out.println("Record offset " + record.offset());
				System.out.println(record);
			});
			consumer.commitAsync();
		}
		consumer.close();
	}

	static void runProducer() throws InterruptedException, ExecutionException {
		Producer<Long, String> producer = ProducerCreator.createProducer();
		DBCursor cursor =  conexion.mostrarById(8,"Jugar");	
		
		Gson gson = new Gson();
		
		cursor.forEach(
				e -> {
//					System.out.println(e.toString());
//					System.out.println("ID -->"+e.get("id"));
//					System.out.println("ACCION -->"+e.get("accion"));				
					try {
						String json = e.toString();
						ObjectoPojo staff = gson.fromJson(json, ObjectoPojo.class);
						System.out.println("Objecto pojo --> "+staff.toString());

						final ProducerRecord<Long, String> record = new ProducerRecord<Long, String>(IKafkaConstants.TOPIC_NAME,
								"This is record "+staff.toString()  );
						RecordMetadata metadata = producer.send(record).get();

					} catch (InterruptedException | ExecutionException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}				
				);
//		final ProducerRecord<Long, String> record = new ProducerRecord<Long, String>(IKafkaConstants.TOPIC_NAME,
//				"This is record "+cursor.toString()  );		
//		RecordMetadata metadata = producer.send(record).get();
		
		/*for (int index = 0; index < IKafkaConstants.MESSAGE_COUNT; index++) {
			final ProducerRecord<Long, String> record = new ProducerRecord<Long, String>(IKafkaConstants.TOPIC_NAME,
					"This is record " + index);
			try {
				RecordMetadata metadata = producer.send(record).get();
				System.out.println("Record sent with key " + index + " to partition " + metadata.partition()
						+ " with offset " + metadata.offset());
			} catch (ExecutionException e) {
				System.out.println("Error in sending record");
				System.out.println(e);
			} catch (InterruptedException e) {
				System.out.println("Error in sending record");
				System.out.println(e);
			}
		}
		*/
	}

}
