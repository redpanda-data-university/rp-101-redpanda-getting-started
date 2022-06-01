## Cleaning Up Cluster Resources
This page contains a brief summary of the commands we explored in Chapter 2 of the [Hands-on Redpanda, Getting Started course][course-link]. Check out [the full course][course-link] for detailed explanations about the commands.

[course-link]: https://university.redpanda.com/courses/hands-on-redpanda-getting-started


#### Delete a single topic
```sh
rpk topic delete song_likes
```

#### Delete one or more topics matching a pattern
```sh
rpk topic delete --regex "^song.*"
```

#### Stopping a cluster
```sh
rpk container stop
```

#### Purging a cluster and its data
```sh
rpk container purge
```

