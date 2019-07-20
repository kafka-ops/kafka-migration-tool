#!/usr/bin/env bash


CLASSPATH="."

for file in "target/scala-2.13/*" "distribution/*";
do
    CLASSPATH="$CLASSPATH":"$file"
done

# Generic jvm settings you want to add
if [ -z "$KAFKA_MIGRATIONS_OPTS" ]; then
  KAFKA_MIGRATIONS_OPTS=""
fi

# Which java to use
if [ -z "$JAVA_HOME" ]; then
  JAVA="java"
else
  JAVA="$JAVA_HOME/bin/java"
fi

# Memory options
if [ -z "$KAFKA_MIGRATIONS_HEAP_OPTS" ]; then
  KAFKA_MIGRATIONS_HEAP_OPTS="-Xmx256M"
fi

exec $JAVA $KAFKA_MIGRATIONS_HEAP_OPTS -cp $CLASSPATH $KAFKA_MIGRATIONS_OPTS com.purbon.kafka.KafkaMigrationToolCLI "$@"

#java -jar target/scala-2.13/kafka-migration-tool-assembly-0.0.1.jar migrate -s http://localhost:28081