const { Kafka } = require('kafkajs')

// create a Kafka client
const kafka = new Kafka({
    brokers: process.env.REDPANDA_BROKERS.split(',')
})

// get a consume instance
const consumer = kafka.consumer({ groupId: 'test-group' })

const run = async () => {
    await consumer.connect()

    // start reading from the beginning of the topic
    await consumer.subscribe({ topic: 'greetings', fromBeginning: true })

    // start the consume and print each message to the screen
    await consumer.run({
        eachMessage: async ({ topic, partition, message }) => {
            console.log({
                partition,
                offset: message.offset,
                value: message.value.toString(),
            })
        },
    })
}

run().catch(console.error)