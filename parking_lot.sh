#!/bin/sh
arg1=$1
##directory where jar file is located    
dir=target
##jar file name
jar_name=parkingLot.jar

mvn clean install -DskipTests

if [ -z "$1" ] ; then
        java -jar $dir/$jar_name
        exit 1

else
	java -jar $dir/$jar_name $arg1

fi
