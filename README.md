# ZIO HTTP Scenarios

## Testing Server Behavior When Client Times Out

- `sbt run` Starts the server on port `8080`.

### 2 Second timeout

```
❯ curl --max-time 2.00 http://localhost:8080/slow
curl: (28) Operation timed out after 2002 milliseconds with 0 bytes received
```    

#### Server logs

```
[info] running (fork) HelloWorld 
[info] Starting slow response...
```

### No timeout

```shell
❯ curl http://localhost:8080/slow
Sorry I took so long :(
```    

#### Server logs

```shell
sbt:zio-http-scenarios> run
[info] running (fork) HelloWorld 
[info] Starting slow response...
[info] Finished slow response
```

This demonstrates that using the default ZIO HTTP 1.x configuration, Netty will silently interrupt a long-running operation if the client terminates prior to the operation completing.
