/* Redpanda University 2022 */
package com.example;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

public class ConsumerExample {
    // be sure to set the REDPANDA_BROKERS variable accordingly
    private static String bootstrapServers = System.getenv("REDPANDA_BROKERS");
    private static String topicName = "greetings";

    private static Properties getConsumerConfig() {
        // Specify the configuration for our consumer
        Properties properties = new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);

        // Specify the consumer group
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, "group-1");

        // Read from the beginning of the topic
        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        // Both keys and values in the greetings topic will be deserialized as strings
        properties.put(
                ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.put(
                ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        return properties;
    }

    public static void main(String args[]) {
        Properties consumerConfig = getConsumerConfig();

        // Get a Consumer instance
        try (final KafkaConsumer<Integer, String> consumer = new KafkaConsumer<>(consumerConfig)) {

            // Subscribe to the greetings topic
            consumer.subscribe(Collections.singletonList(topicName));

            // Consume from the source topic continuously
            while (true) {
                ConsumerRecords<Integer, String> records = consumer.poll(Duration.ofMillis(1000));

                // Print records as they come in
                for (ConsumerRecord<Integer, String> record : records) {
                    System.out.printf(
                            "Consumed record. key=%s, value=%s\n", record.key(), record.value());
                }
            }
        }
    }
}
