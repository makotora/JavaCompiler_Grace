#!/bin/bash

if [ "$#" -lt 1 ]; then
    echo "You must give a directory as the first argument!"
    exit -1
fi

files=$(ls $1)

for file in $files
do
	echo -n "Compiling: "
	echo $file
	echo

	filePath=$1$file

	./grace.sh executable $filePath
	rc=$?
	echo

	#if compilation failed.Dont run
	if [[ $rc != 0 ]]; then 
		echo "<!>Compilation failed!"
	else
		echo "Running"
		./executable
		rm executable
	fi
	
	echo
done

exit 0