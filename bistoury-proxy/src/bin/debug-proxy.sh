#!/bin/bash
java
-Dhll.env=prd
-Xmx4G
-Xms4G
-XX:+UseG1GC
-XX:MaxGCPauseMillis=200
-XX:InitiatingHeapOccupancyPercent=45
-XX:G1ReservePercent=10
-XX:+UseStringDeduplication
-XX:MetaspaceSize=256m
-XX:MaxMetaspaceSize=256m
-Xloggc:/home/data/logs/proxy-gc.log
-XX:+UseGCLogFileRotation
-XX:NumberOfGCLogFiles=10
-XX:GCLogFileSize=10M
-XX:+PrintGCDetails
-XX:+PrintGCDateStamps
-XX:+HeapDumpOnOutOfMemoryError
-XX:HeapDumpPath=/home/data/logs/heapdump.hprof

-Dbistoury.conf=/home/data/webroot/ci-debug-svc/conf
-cp /home/data/webroot/ci-debug-svc/lib/*:$JAVA_HOME/lib/tools.jar:$JAVA_HOME/lib/sa-jdi.jar:/home/data/webroot/ci-debug-svc/conf
qunar.tc.bistoury.proxy.container.Bootstrap

