const { Kafka, Partitioners } = require('kafkajs')

// create a Kafka client
const kafka = new Kafka({
    brokers: process.env.REDPANDA_BROKERS.split(',')
})

// get a producer instance
const producer = kafka.producer(
    { createPartitioner: Partitioners.LegacyPartitioner }
)

const run = async () => {
    await producer.connect()

    // produce a greeting to the topic
    await producer.send({
        topic: 'greetings',
        messages: [
            { value: 'Hello, World!' },
        ]
    })

    console.log("Produced greeting to topic")

    await producer.disconnect()
}

run().catch(console.error)