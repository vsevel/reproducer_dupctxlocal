to reproduce the issue:
  - start myauth: `cd myauth ; mvn compile quarkus:dev`
  - start dupctxlocal: `cd dupctxlocal ; mvn compile quarkus:dev`
  - load `test_js 2.html` in chrome

you can validate everything is running with `curl -X POST -H "Authorization: Basic X" localhost:8090/hello/post -v`, but you need to restart dupctxlocal before you refresh `test_js 2.html`