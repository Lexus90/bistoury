#!/bin/bash


java
-Dhll.env=prd
-Xmx100m -Xmn50m -XX:+UseParallelGC -XX:+UseParallelOldGC -XX:+UseCodeCacheFlushing
-Xloggc:/home/data/logs/agent-gc.log
-XX:+PrintGC -XX:+PrintGCDetails -XX:+HeapDumpOnOutOfMemoryError
-XX:HeapDumpPath=/home/data/logs/ci-debug-agent/heapdump.hprof

-Dbistoury.store.path=/home/data/webroot/ci-debug-agent/store
-Dbistoury.proxy.host=127.0.0.1:9090
-Dbistoury.app.lib.class=org.springframework.web.servlet.DispatcherServlet
-Dbistoury.log.dir=/home/data/logs/ci-debug-agent

-cp /home/data/webroot/ci-debug-agent/lib/*:$JAVA_HOME/lib/tools.jar:$JAVA_HOME/lib/sa-jdi.jar:/home/data/webroot/ci-debug-agent/conf
qunar.tc.bistoury.indpendent.agent.Main





