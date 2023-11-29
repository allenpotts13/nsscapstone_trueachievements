#!/bin/bash

inputDir="/home/viking/TrueAchievements/nsscapstone_trueachievements/data/csvtojson_batches"
batchSize=25

files=("$inputDir"/*)

for ((i = 0; i < ${#files[@]}; i += batchSize)); do
	start=$i
end=$((i + batchSize - 1))

batch_files=("${files[@]:start+1:start+batchSize}")

for file in "${batch_files[@]}";do
	aws dynamodb batch-write-item --request-items "file://${file}"
done
done
