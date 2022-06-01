## Working with Redpanda topics
This page contains a brief summary of the commands we explored in Chapter 2 of the [Hands-on Redpanda, Getting Started course][course-link]. Check out [the full course][course-link] for detailed explanations about the commands.

[course-link]: https://university.redpanda.com/courses/hands-on-redpanda-getting-started

#### Setup
```sh
docker-compose up -d && \
export REDPANDA_BROKERS="localhost:9092" && \
alias rpk="docker exec -ti redpanda-1 rpk"
```

#### Create topics
```sh
rpk topic create song_plays song_likes --partitions 6 --replicas 1
```

#### List topics
```sh
rpk topic list
```

#### List detailed information about topics
```sh
rpk topic list --detailed
```

#### Describe topics:
```sh
rpk topic describe song_plays
```

#### Re-configure topics
```sh
rpk topic alter-config song_plays --set retention.ms=259200000
```

#### Print topic configurations only
```sh
rpk topic describe song_plays -c
```

#### Updating partition counts
```sh
rpk topic add-partitions song_plays -n6
```

#### View a summary of the topic
```sh
rpk topic describe song_plays -s
```

#### Produce data to a topic
```sh
rpk topic produce song_likes
```

When prompted, type the following:
```json
{"song_id": 1234, "event": "like"}
{"song_id": 5678, "event": "like"}
```

#### Produce keys and values
```sh
rpk topic produce song_likes --format "%k|%v\n"
```

When prompted, type the following:
```
key1|{"song_id": 1234, "event": "like"}
key2|{"song_id": 5678, "event": "like"}
```

#### Consuming data
```sh
rpk topic consume song_likes
```

#### Consuming data from different starting points
```sh
rpk topic consume song_likes --offset 1 
rpk topic consume song_likes --offset start 
rpk topic consume song_likes --offset end
```

#### Consuming just the record keys
```sh
rpk topic consume song_likes --offset start --format "%k\n"
```

#### Consuming a fixed number of messages
```sh
rpk topic consume song_likes --offset start --num 1
```

#### Consuming from a specific partition
```sh
rpk topic consume song_likes --partitions 4
```

