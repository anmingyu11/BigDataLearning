package _03Collections

object TupleApp {
  def main(args: Array[String]): Unit = {
    val a = (1,2,3,4)
    for (i<-0.until(a.productArity)){
      println(a.productElement(i))
    }
    println(a._1)
    println(a.productElement(3))
    val hostPort = ("localhost",8080)

  }

}
