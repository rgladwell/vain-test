package me.gladwell.vain

import org.junit.runner.RunWith
import org.scalatest.FlatSpec
import org.scalatest.GivenWhenThen
import org.scalatest.Matchers
import org.scalatest.junit.JUnitRunner

import me.gladwell.vain._

@RunWith(classOf[JUnitRunner])
class DependenciesSpec extends FlatSpec with Matchers with GivenWhenThen with Classpath {

  "A Vain module" should "resolve and add Java library dependencies to the runtime classpath" in {
    Given("a module with a dependency on the commons-lang Java library")
    object testModule extends ScalaModule {
      dependsOn(Seq(Dep(group = "commons-lang", name = "commons-lang", version = "2.4")))
    }

    When("the module is loaded")
    testModule

    Then("the classpath should contain the commons-lang jar")
    exactly(1, classpath) should be (dependency(Dep(group = "commons-lang", name = "commons-lang", version = "2.4")))
  }

  "A Vain module" should "resolve and add Java library dependencies to the compile-time classpath" in {
    Given("a module with a dependency on the commons-lang Java library")
    object testModule extends ScalaModule {
      dependsOn(Seq(Dep(group = "commons-lang", name = "commons-lang", version = "2.4")))
    }

    When("the module is loaded")
    testModule

    Then("commons-lang utilities should be available")
    import org.apache.commons.lang.StringUtils.upperCase
    upperCase("test") should be ("TEST")
  }

}
