# To reproducuce:

run `sbt`, `runAll`, then hit http://localhost:9000/api/hello/andy

also fails with `sbt -J-javaagent:/home/andrew/.coursier/cache/v1/https/repo1.maven.org/maven2/org/aspectj/aspectjweaver/1.8.13/aspectjweaver-1.8.13.jar` followed by `runAll`.

If future propogation is working, you should see "andy" (or whatever you pass as a path param) echo'd. If it isn't working, you'll see "default value" echo'd.

# Notes:
This project has been generated by the lagom/lagom-scala.g8 template. 

For instructions on running and testing the project, see https://www.lagomframework.com/get-started-scala.html.
