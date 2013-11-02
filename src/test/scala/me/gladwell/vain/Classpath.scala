package me.gladwell.vain

import java.net.URLClassLoader
import java.net.URL

import org.scalatest.matchers.BeMatcher
import org.scalatest.matchers.MatchResult

import me.gladwell.vain.dependencies._

trait Classpath {

  private def thread = Thread.currentThread
  private def classLoader: URLClassLoader = thread.getContextClassLoader.asInstanceOf[URLClassLoader]

  def classpath = classLoader.getURLs()

  def dependency(dependency: Dependency): BeMatcher[URL] = new BeMatcher[URL] {
    def apply(url: URL) = MatchResult(
      dependency match {
        case JavaLibrary(organisation, name, version) => url.toString.endsWith(s"${name}-${version}.jar")
        case _ => false
      },
      s"$url did not match dependency $dependency",
      s"$url match dependency $dependency"
    )
  }

}
