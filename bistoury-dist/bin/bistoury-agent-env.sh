#!/bin/bash
set -euo pipefail

BISTOURY_STORY_PATH="$BISTOURY_STORE_DIR"
BISTOURY_PROXY_HOST="127.0.0.1:9090"
BISTOURY_APP_LIB_CLASS="org.springframework.web.servlet.DispatcherServlet"
#BISTOURY_APP_LIB_CLASS="cn.huolala.test.DemoWebApplication"
#-Dbistoury.app.lib.class=cn.huolala.test.DemoWebApplication
#JAVA_HOME="/tmp/bistoury/java"
JAVA_OPTS="-Dbistoury.store.path=$BISTOURY_STORY_PATH -Dbistoury.proxy.host=$BISTOURY_PROXY_HOST -Dbistoury.pid.handler.jps.symbol.class=/Users/james.cao/code/lala-jaf-monitor-demo/test-web/target/ci-jaf-demo-svc-1.0.0-SNAPSHOT.jar "
