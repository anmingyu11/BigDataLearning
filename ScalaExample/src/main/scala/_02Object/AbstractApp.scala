package _02Object

object AbstractApp {

  def main(args: Array[String]): Unit = {
    val student = new Student2()
    student.speak
  }

}

abstract class Person2 {
  def speak

  val name: String
  val age: Int
}

class Student2 extends Person2 {
  override def speak: Unit = {
    println("fuck " + name + " age : " + age)
  }

  override val name: String = "ss"
  override val age: Int = 23
}
