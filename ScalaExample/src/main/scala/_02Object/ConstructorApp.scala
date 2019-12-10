package _02Object

object ConstructorApp {
  def main(args: Array[String]): Unit = {
    val people = new People()
    println(people.name + people.age + people.gender)
    val stu = new Student("ssss", 12, "sti");
    println(stu.gender)
    println(stu.name)
    println(stu.scoole)

  }
}

class People(val name: String,val age :Int) {
  println("gogogo fuck")
  val scoole = "heida"
  var gender: String = _

  def this(name: String="ss", age: Int=12, gender: String="female") {
    this(name, age) // 第一行必须要调用主构造器或其他附属构造器
    this.gender = gender
  }

  println("fuck gogogo")
}

class Student(name: String, age: Int, student: String) extends People(name, age) {
  override val scoole = "abc"

}
