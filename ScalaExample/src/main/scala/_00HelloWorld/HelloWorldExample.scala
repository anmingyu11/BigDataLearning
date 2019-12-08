package _00HelloWorld

object HelloWorldExample {

  def main(args: Array[String]): Unit = {
    println("hello world")
    println(add(1,2))
  }

  def add(a:Int,b:Int):Int={
    a+b
  }

}
