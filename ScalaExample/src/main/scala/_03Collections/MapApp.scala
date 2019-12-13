package _03Collections

import scala.collection.mutable

object MapApp extends App {

  val a = Map("ss" -> 13)
  val b = Map("nn" -> 23)
  println(a("ss"))
  println(b("nn"))
  val c = mutable.HashMap[String, Int]()
  // 可以+= 可以 -=
  println(c)
  c += ("nina" -> 28)
  c += ("anna" -> 28)
  println(c)
  c -= ("anna")
  c += ("sisi" -> 23)
  println(c)

  for ((key, value) <- c) {
    println(key + "  : " + value)
  }
  println("----")
  for (ele <- c.keySet) {
    println(ele + " : " + c.getOrElse(ele, 10))
  }
  println("----")
  for (v <- c.values){
    println(v)
  }
  println("----")
  for((key,_) <- c){
    println(key)
  }

}
