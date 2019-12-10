package _02Object

// 通常用在模式匹配
object CaseClassApp{
  def main(args: Array[String]): Unit = {
    println(new Dog().name)
    println(new Dog("asdasd").name)
  }
}

// case class 不用 new
case class Dog(name:String="wangcai")
