//
// Copyright (C) 2016 Lightbend Inc. <https://www.lightbend.com>
//

// The Lagom plugin
addSbtPlugin("com.lightbend.lagom" % "lagom-sbt-plugin" % "1.2.2")
// Needed for importing the project into Eclipse
addSbtPlugin("com.typesafe.sbteclipse" % "sbteclipse-plugin" % "5.1.0")
// The ConductR plugin
addSbtPlugin("com.lightbend.conductr" % "sbt-conductr" % "2.1.16")
//jacoco for coverage
addSbtPlugin("de.johoop" % "jacoco4sbt" % "2.2.0")
//cpd
addSbtPlugin("de.johoop" % "cpd4sbt" % "1.2.0")
//findbugs
addSbtPlugin("de.johoop" % "findbugs4sbt" % "1.4.0")