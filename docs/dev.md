
## 一 配置本地启动 模块参数

### 1. Agent

Main: `qunar.tc.bistoury.indpendent.agent.Main`

VM options:
```shell script
-Dbistoury.proxy.host=127.0.0.1:9090
-Dbistoury.pid.handler.jps.symbol.class=/Users/james.cao/code/lala-jaf-monitor-demo/test-web/target/ci-jaf-demo-svc-1.0.0-SNAPSHOT.jar
-Dbistoury.lib.dir=/Users/james.cao/code/debug/bistoury/bistoury-dist/target/bistoury-agent-bin/lib
-Dbistoury.agent.jar.path=/Users/james.cao/code/debug/bistoury/bistoury-dist/target/bistoury-agent-bin/lib/bistoury-instrument-agent.jar
-Dbistoury.agent.jar.path=/Users/james.cao/code/debug/bistoury/bistoury-dist/target/bistoury-agent-bin/lib/bistoury-instrument-agent-2.0.7.jar
-Dbistoury.arthas.core.jar.path=/Users/james.cao/code/debug/bistoury/bistoury-dist/target/bistoury-agent-bin/lib/arthas-core-3.1.4.jar
-Dbistoury.app.lib.class=cn.huolala.test.DemoWebApplication

```

## 2. UI 

Main: `qunar.tc.bistoury.ui.container.Bootstrap`

```shell script
-Dbistoury.conf=/Users/james.cao/code/debug/bistoury/bistoury-ui/conf
```

## 3. Proxy 

Main: `qunar.tc.bistoury.proxy.container.Bootstrap`

```shell script
-Dbistoury.conf=/Users/james.cao/code/debug/bistoury/bistoury-proxy/conf
```


## 二 创建proxy.conf文件

路径:/tmp/bistoury/proxy.conf

内容:
```shell script
172.30.84.204:9090:9881

LOCAL_IP=`/sbin/ifconfig -a|grep inet|grep -v 127.0.0.1|grep -v inet6|awk '{print $2}'|tr -d "addr:"|tail -1`
PROXY_TOMCAT_PORT=9090
PROXY_WEBSOCKET_PORT=9881;
echo "$LOCAL_IP:$PROXY_TOMCAT_PORT:$PROXY_WEBSOCKET_PORT">$BISTOURY_PROXY_CONF_FILE
```



