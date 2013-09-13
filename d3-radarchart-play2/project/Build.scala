import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {

  val appName         = "d3-radarchart-play2"
  val appVersion      = "1.0-SNAPSHOT"

  val appDependencies = Seq(
    
    // gson
    "com.google.code.gson" % "gson" % "2.2.4",
      
    // webjars for play
    "org.webjars" %% "webjars-play" % "2.1.0-3",

	// webjar components
	"org.webjars" % "jquery" % "2.0.3",
	"org.webjars" % "bootstrap" % "3.0.0",
	"org.webjars" % "d3js" % "3.1.5",
  
  	// d3 radarchart needs to be loaded from file 
  	// future option: package and add webjar for radar chart
  
    // Add your project dependencies here,
    javaCore,
    javaJdbc,
    javaEbean
  )

  val main = play.Project(appName, appVersion, appDependencies).settings(
    // Add your own project settings here      
  )

}
