#!/usr/bin/env bash

function apply_antlr () {
 rm -f  com/purbon/kafka/migrations/grammar/*
 # apply antlr
 antlr -package com.purbon.kafka.migrations.grammar  *.g4
 # move the generated file to their source
 mv *.java   com/purbon/kafka/migrations/grammar
 mv *.interp com/purbon/kafka/migrations/grammar
 mv *.tokens com/purbon/kafka/migrations/grammar
 true;
}

( cd "src/main/antlr"; apply_antlr )