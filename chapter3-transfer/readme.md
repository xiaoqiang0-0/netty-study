# nio oio 服务器程序

### `maven`启动

```bash
mvn exec:java
```

### `telnet` 连接

> telnet 连接命令
> `telnet host port`

```bash
telnet localhost 10000 #连接java 实现oio
telnet localhost 10001 #连接java 实现nio
telnet localhost 10002 #连接netty 实现oio
telnet localhost 10003 #连接netty 实现nio
```