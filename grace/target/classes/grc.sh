#!/bin/bash

grace_file=$1
file_name=${grace_file%.*}
extension=".s"
assembly_name=$file_name$extension

java compiler.Main $grace_file
gcc -m32 -o $file_name $assembly_name
