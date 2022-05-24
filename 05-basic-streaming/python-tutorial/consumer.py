"""
A basic example of a Redpanda producer
"""
import os
from dataclasses import dataclass

from kafka import KafkaConsumer


@dataclass
class ConsumerConfig:
    bootstrap_servers = os.getenv("REDPANDA_BROKERS", "")
    topic = "greetings"
    consumer_group = "my-group"
    auto_offset_reset = "earliest"


class Consumer:
    def __init__(self, config: KafkaConsumer):
        self.client = KafkaConsumer(
            "greetings",
            group_id=config.consumer_group,
            auto_offset_reset=config.auto_offset_reset,
            # add more configs here if you'd like
        )
        self.topic = config.topic

    def consume(self):
        """Consume messages from a Redpanda topic"""
        try:
            for msg in self.client:
                print(f"Consumed record. key={msg.key}, value={msg.value}")
        except:
            print("Could not consume from topic: {self.topic}")
            raise


config = ConsumerConfig()
redpanda_consumer = Consumer(config)
redpanda_consumer.consume()
