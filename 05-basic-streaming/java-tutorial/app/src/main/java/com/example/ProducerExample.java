/* Redpanda University 2022 */
package com.example;

import java.util.Properties;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

public class ProducerExample {
    // be sure to set the REDPANDA_BROKERS variable accordingly
    private static String bootstrapServers = System.getenv("REDPANDA_BROKERS");
    private static String topicName = "greetings";

    private static Properties getProducerConfig() {
        // Specify the configuration for our producer
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        properties.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, false);

        // Both keys and values in the greetings topic will be serialized as strings
        properties.put(
                ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(
                ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        return properties;
    }

    public static void main(String args[]) {
        Properties producerConfig = getProducerConfig();

        // Get a Producer instance
        try (KafkaProducer<String, String> producer = new KafkaProducer<>(producerConfig)) {

            // Construct the greeting record
            ProducerRecord<String, String> record =
                    new ProducerRecord<>(topicName, "key1", "Hello, World!");

            // Produce the record to the source topic
            producer.send(record);

            // producer.send() is async, to make sure all messages are sent we use producer.flush()
            producer.flush();
            producer.close();
            System.out.printf("Produced record. key=%s, value=%s\n", record.key(), record.value());

        } catch (Exception e) {
            System.err.println("Error: exception " + e);
        }
    }
}
