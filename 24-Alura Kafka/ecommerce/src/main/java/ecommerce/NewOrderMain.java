package ecommerce;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class NewOrderMain {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        try (KafkaProducer<String, String> producer = new KafkaProducer<>(properties())) {
            String value = "132123,312312,3123123123";
            ProducerRecord<String, String> recordToSend = new ProducerRecord<>("ECOMMERCE_NEW_ORDER", value, value);
            producer.send(recordToSend, (data, ex) -> {
                if (ex != null) {
                    ex.printStackTrace();
                }
                System.out.println(data.topic() + ":::" + data.partition() + "/" + data.offset() + "/" + data.timestamp());
            }).get();
        }
    }

    private static Properties properties() {
        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"127.0.0.1:9092");
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        return properties;
    }
}
