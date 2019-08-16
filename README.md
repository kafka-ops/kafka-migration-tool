# The Kafka Migration tool

If you're a developer building Kafka Streams applications or an devops responsible of operating an 
Apache Kafka cluster you certainly have the need of moving changes between stages.

How should I move changes in Schema Registry from staging to production? How should we manage the 
creation, deletion or config topic changes when deploying a new Kafka Application (Streams, Connector
or just a simple Consumers/Producers app) all the way to production? 

All this fairly common problems every team finds.

A classical solution to this is to maintain a yaml, that the teams change to request changes and the
CI/CD infrastructure is in charge to setup.

The way the Kafka Migration tool (yeah, I did not work much on the naming) propose to solve this problem is using migrations. 
Let's have the flywaydb of Apache Kafka.
# flywaydb - that's an interesting reference. Are you thinking to support migrations versions?


*NOTE:* This project is in active development, api's and feature sets are in constant evolution.

## The CLI

This project is available as a CLI, with a set of current command available:

```bash
➜  kafka-migration-tool git:(master) ✗ ./bin/kafka-migration-tool.sh --help
Error: Unknown option --help
Usage: Kafka Migration Tool [migrate|migrate:up|migrate:down|clean] [options]

  -b, --brokers url <value>
                           Kafka brokers location
  -s, --schema-registry url <value>
                           Schema Registry destination url
  -m, --migrations url <value>
                           Where to find the stored migrations
Command: migrate
apply all migrations sequencially
Command: migrate:up
apply a selected migration change request
Command: migrate:down
remove a selected migration change request
Command: clean
clean command
```

## Components supported

As of the current version, the Kafka Migration tool support:

* Change Request to the Confluent Schema Registry
* Change Request to topic management.

In the future, it will support as well:

* Change Request for ACL's
* Change Request for users, ....

## Willing to use the Kafka Migration tool?

If you would like to use the kafka migration tool, there are a few options available for you:

### Building from the source code

To build from the source code you will need sbt and scala 2.13 available in your environment. This commands will be helpful for you:

* _sbt clean assembly_ : this will generate a far jar with all necessary code and dependencies packaged
* _sbt test_: will run the test suite available

generated packages will be available for you in the target/ directory.

### Download a pre-packaged release

Currently available packages are ready for you in the [release](https://github.com/purbon/kafka-migration-tool/releases) page, feel free to download
them and use it.

## Check out the wiki for more documentation

For more information on how to use the migration tool, please check the [wiki](https://github.com/purbon/kafka-migration-tool/wiki)

## Contributing

All contributions are welcome: ideas, patches, documentation, bug reports,
complaints, etc!

Programming is not a required skill, and there are many ways to help out!
It is more important to us that you are able to contribute.

That said, [some basic guidelines](CONTRIBUTING.md), which you are free to ignore :)
