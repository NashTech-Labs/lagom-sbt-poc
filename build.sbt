organization in ThisBuild := "sample.helloworld"

// the Scala version that will be used for cross-compiled libraries
scalaVersion in ThisBuild := "2.11.8"

// SCALA SUPPORT: Remove the line below
EclipseKeys.projectFlavor in Global := EclipseProjectFlavor.Java

lazy val commonSettings = libraryDependencies ++= Seq("org.projectlombok" % "lombok" % "1.16.14",
  "com.lightbend.lagom" % "lagom-javadsl-jackson_2.11" % "1.0.0-RC1")

lazy val helloworldApi = project("helloworld-api")
  .settings(
    version := "1.0-SNAPSHOT",
    libraryDependencies += lagomJavadslApi
  )

lazy val helloworldImpl = project("helloworld-impl")
  .enablePlugins(LagomJava)
  .settings(
    version := "1.0-SNAPSHOT",
    libraryDependencies ++= Seq(
      lagomJavadslPersistenceCassandra,
      lagomJavadslTestKit,
      lagomJavadslBroker,
      lagomJavadslKafkaBroker,
      lagomJavadslKafkaClient
    )
  )
  .settings(lagomForkedTestSettings: _*)
  .dependsOn(helloworldApi)

lazy val userApi = project("user-api")
  .settings(
version := "1.0-SNAPSHOT",
libraryDependencies += lagomJavadslApi
).settings(commonSettings: _*)

lazy val userImpl = project("user-impl")
  .enablePlugins(LagomJava)
  .settings(
version := "1.0-SNAPSHOT",
libraryDependencies ++= Seq(
lagomJavadslPersistenceCassandra,
lagomJavadslTestKit
)
)
  .settings(lagomForkedTestSettings: _*)
  .settings(commonSettings: _*)
  .dependsOn(userApi)


def project(id: String) = {
  Project(id, base = file(id))
    .settings(javacOptions in compile ++= Seq("-encoding",
      "UTF-8",
      "-source",
      "1.8",
      "-target",
      "1.8",
      "-Xlint:unchecked",
      "-Xlint:deprecation"))
    .settings(jacksonParameterNamesJavacSettings: _*)
} // applying it to every project even if not
// strictly needed.


// See https://github.com/FasterXML/jackson-module-parameter-names
lazy val jacksonParameterNamesJavacSettings = Seq(
  javacOptions in compile += "-parameters"
)

lagomKafkaEnabled in ThisBuild := false
lagomKafkaAddress in ThisBuild := "localhost:9092"

lagomCassandraEnabled in ThisBuild := false
lagomUnmanagedServices in ThisBuild := Map("cas_native" -> "http://localhost:9042")