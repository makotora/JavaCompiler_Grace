#!/bin/bash

if [ "$#" -lt 2 ]; then
    echo "Illegal number of parameters!Must be 2 or 3"
fi

executable_name=$1
grace_file=$2

file_path=${grace_file%.*}
name="${file_path##*/}"

S_extention=".s"
Q_extention=".quads"
QS_extention=".qs"

assembly_path=$file_path$S_extention
quads_path=$file_path$Q_extention
qs_path=$file_path$QS_extention

assembly_name=$name$S_extention
quads_name=$name$Q_extention
qs_name=$name$QS_extention

#compile the grace file (this outputs .s .qs .quads files on the same dir as the .grace file)

if [ "$#" -eq 2 ]; then #WITH optimization
	java -cp ./target/classes compiler.Main $grace_file
else #NO optimization, java program uses no optimization if it received 2 arguments (doesnt matter what the second one is)
	echo Extra argument detected.Compiling without optimization!
	echo
	java -cp ./target/classes compiler.Main $grace_file dontOptimize
fi


#if compilation didnt go well..
rc=$?; if [[ $rc != 0 ]]; then exit $rc; fi

#make a directory in the current directory with the name of the file compiling
mkdir -p $name
#and put/mv all files generated besides the executable inside
#(quads assembly quads_assembly)
mv $assembly_path "$name"/"$assembly_name"
mv $quads_path "$name"/"$quads_name"
mv $qs_path "$name"/"$qs_name"

#finally compile the assembly file
gcc -m32 -o $executable_name "$name"/"$assembly_name"