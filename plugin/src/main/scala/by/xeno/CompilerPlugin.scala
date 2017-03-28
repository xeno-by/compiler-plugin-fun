package by.xeno

import scala.tools.nsc.{Global, Phase}
import scala.tools.nsc.plugins.{Plugin, PluginComponent}

class CompilerPlugin(val global: Global) extends Plugin { plugin =>
  import global._

  val name = "compiler-plugin"
  val description = "Getting started with scalac internals"
  val components = List[PluginComponent](FunComponent)

  private object FunComponent extends PluginComponent {
    val global: plugin.global.type = plugin.global
    val runsAfter = List("typer")
    val phaseName = "phase"

    override def newPhase(prev: Phase) = new FunPhase(prev)

    class FunPhase(prev: Phase) extends StdPhase(prev) {
      override def apply(unit: CompilationUnit): Unit = {
        println("hello from a compiler plugin")
      }
    }
  }
}