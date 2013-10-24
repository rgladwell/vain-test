package me.gladwell.vain

import org.scalatest.FlatSpec
import org.scalatest.GivenWhenThen
import org.scalatest.Matchers

import me.gladwell.vain._
import me.gladwell.vain.dependencies.JavaLibrary

class DependenciesSpec extends FlatSpec with Matchers with GivenWhenThen with Classpath {

  "A Vain module" should "resolve and add Java library dependencies to the classpath" in {
    Given("a module with a dependency on the commons-lang Java library")
    object TestModule extends Module {
      println("hello, world!")
      dependsOn(Seq(JavaLibrary(group = "commons-lang", name = "commons-lang", version = "2.4")))
    }

    Then("the classpath should contain the commons-lang jar")
    exactly(1, classpath) should be (dependency(JavaLibrary(group = "commons-lang", name = "commons-lang", version = "2.4")))
  }

}
