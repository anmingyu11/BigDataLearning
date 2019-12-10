object SimpleObjectApp {

  def main(args: Array[String]): Unit = {
    val people = new People();
    people.name = "ss"
    println(people.name)
    println(people.age)

    println("invoking : " + people.eat())
    people.watchFootball("asdasd")
    people.printInfo()
  }

}

class People {

  var name: String = _ // 占位符
  val age: Int = 10 // 固定类型

  private[this] val gender = "male"

  def printInfo(): Unit ={
    println("gender: " + gender)
  }
  def eat(): String = {
    name + "eat"
  }

  def watchFootball(team: String): Unit = {
    println(name + "is watching game of " + team)

  }

}
