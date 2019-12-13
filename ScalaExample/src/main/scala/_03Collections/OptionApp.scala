package _03Collections

import scala.collection.mutable

object OptionApp {
  def main(args: Array[String]): Unit = {
    val map = mutable.Map("nina" -> 29)
    println(map)
    println(map.get("nina").get)
    println(map.getOrElse("sisi","fuc"))
    println(map)
  }
}
