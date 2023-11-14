to reproduce the issue:
  - start myauth: `cd myauth ; mvn compile quarkus:dev`
  - start dupctxlocal: `cd dupctxlocal ; mvn compile quarkus:dev`
  - load `test_js 2.html` in chrome

you should see:
```
2023-11-14 13:03:50,914 ERROR [org.acm.MyFilter] (vert.x-eventloop-thread-1) =========> ctx already initialized!!!
2023-11-14 13:03:50,932 ERROR [io.qua.ver.htt.run.QuarkusErrorHandler] (vert.x-eventloop-thread-1) HTTP Request to /hello/post failed, error id: 8fe58911-afe2-4588-bb50-18ed88852249-1: java.lang.RuntimeException: oops
	at org.acme.MyFilter.preMatchingFilter(MyFilter.java:44)
	at org.acme.MyFilter$GeneratedServerRequestFilter$preMatchingFilter.filter(Unknown Source)
	at org.acme.MyFilter$GeneratedServerRequestFilter$preMatchingFilter_ClientProxy.filter(Unknown Source)
	at org.jboss.resteasy.reactive.server.handlers.ResourceRequestFilterHandler.handle(ResourceRequestFilterHandler.java:48)
	at io.quarkus.resteasy.reactive.server.runtime.QuarkusResteasyReactiveRequestContext.invokeHandler(QuarkusResteasyReactiveRequestContext.java:131)
	at org.jboss.resteasy.reactive.common.core.AbstractResteasyReactiveContext.run(AbstractResteasyReactiveContext.java:147)
	at org.jboss.resteasy.reactive.server.handlers.RestInitialHandler.beginProcessing(RestInitialHandler.java:48)
	at org.jboss.resteasy.reactive.server.vertx.ResteasyReactiveVertxHandler.handle(ResteasyReactiveVertxHandler.java:23)
	at org.jboss.resteasy.reactive.server.vertx.ResteasyReactiveVertxHandler.handle(ResteasyReactiveVertxHandler.java:10)
	at io.vertx.ext.web.impl.RouteState.handleContext(RouteState.java:1286)
    ...
```

you can validate everything is running with `curl -X POST -H "Authorization: Basic X" localhost:8090/hello/post -v`, but you need to restart dupctxlocal before you refresh `test_js 2.html`