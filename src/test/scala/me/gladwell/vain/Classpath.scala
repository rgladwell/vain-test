package me.gladwell.vain

import java.net.URLClassLoader
import java.net.URL

import org.scalatest.matchers.BeMatcher
import org.scalatest.matchers.MatchResult

import me.gladwell.vain.dependencies._

trait Classpath {

  private val thread = Thread.currentThread
  private val classLoader: URLClassLoader = thread.getContextClassLoader.getParent.asInstanceOf[URLClassLoader]

  val classpath = classLoader.getURLs()

  def dependency(dependency: Dependency): BeMatcher[URL] = new BeMatcher[URL] {
    def apply(url: URL) = MatchResult(
      dependency match {
        case JavaLibrary(organisation, name, version) => url.toString.endsWith(s"${organisation}.${name}-${version}.jar")
        case _ => false
      },
      s"$url did not match dependency $dependency",
      s"$url match dependency $dependency"
    )
  }

}
