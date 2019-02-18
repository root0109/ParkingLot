#!/bin/sh
arg1=$1
##directory where jar file is located    
dir=target
##jar file name
jar_name=parkinglot-1.0-SNAPSHOT.jar

mvn clean install 

if [ -z "$1" ] ; then
        java -jar $dir/$jar_name
        exit 1

else
	java -jar $dir/$jar_name $arg1

fi
