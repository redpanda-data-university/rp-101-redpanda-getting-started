## Managing Consumer Groups
This page contains a brief summary of the commands we explored in Chapter 2 of the [Hands-on Redpanda, Getting Started course][course-link]. Check out [the full course][course-link] for detailed explanations about the commands.

[course-link]: https://university.redpanda.com/courses/hands-on-redpanda-getting-started

#### Setup
```sh
docker-compose up -d && \
export REDPANDA_BROKERS="localhost:9092" && \
alias rpk="docker exec -ti redpanda-1 rpk"
```

#### List groups
```sh
rpk group list
```

#### Describe a consumer group
```sh
rpk group describe groupA
```

#### Set the position of a consumer group
```sh
# seek to the beginning of all topics
rpk group seek groupA --to start

# seek to the beginning the song_likes topic
rpk group seek groupA --to start --topics song_likes

# seek to five minutes ago for all topics
rpk group seek groupA --to $(date -v -5M +%s)
```

#### Delete a consumer group
```sh
rpk group delete groupB
```


