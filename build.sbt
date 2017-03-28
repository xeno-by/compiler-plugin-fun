scalaVersion in ThisBuild := "2.11.8"

lazy val plugin = Project(
  id = "plugin",
  base = file("plugin")
) settings (
  libraryDependencies += "org.scala-lang" % "scala-compiler" % scalaVersion.value
)

lazy val app = Project(
  id = "app",
  base = file("app")
) settings (
  scalacOptions ++= {
    val compiledPlugin = (Keys.`package` in (plugin, Compile)).value
    val addPlugin = "-Xplugin:" + compiledPlugin.getAbsolutePath
    val recomputedWhenPluginUpdated = "-Jdummy=" + compiledPlugin.lastModified
    Seq(addPlugin, recomputedWhenPluginUpdated)
  }
)
