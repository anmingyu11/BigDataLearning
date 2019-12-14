package _04Match

object MatchApp extends App {

  // val names = Array("s1","s2","s3")
  // val name = names(Random.nextInt(3))

  // name match{
  //   case s1 => println("match s1")
  //   case s2 => println("match s2")
  //   case _ => println("match nothing")
  // }

  //def judgeGrade(grade:String): Unit ={
  //  grade match {
  //    case "A" => println("case A")
  //    case "B" => println("case B")
  //    case "C" => println("case C")
  //    case _ => println("case Nothing")
  //  }
  //}

  //judgeGrade("A")
  //judgeGrade("B")
  //judgeGrade("D")

  /*
  def judgeGrade(name: String, grade: String): Unit = {
    grade match {
      case "A" => println("A")
      case "B" => println("B")
      case "C" => println("C")
      case _ if (name == "sisi") => println(name + " you are geting fuck")
      case _ => println("you need work harder")
    }
  }

  judgeGrade("nana", "A")
  judgeGrade("sisi", "D")
  */

  // 数组的匹配

  //def greeting(array: Array[String]): Unit = {
  //  array match {
  //    case Array("nina") => println("hi : nina")
  //    case Array(x, y) => println(x + " : " + y)
  //    case Array("ss", _*) => println("Hi ss and" + " others .")
  //    case _ => println("others")
  //  }
  //}

  //greeting(Array("nina", "sisi"))
  //greeting(Array("nina"))
  //greeting(Array("ss", "sisi","sssss"))
  //greeting(Array("sisi"))

  /*
  def greeting(list:List[String]): Unit ={
    list match {
      case "cammy"::Nil => println("cammy")
      case x::y::Nil =>println(x + " : " + y)
      case "nina"::tail =>println("nina and others")
      case _ => println(" others")
    }
  }

  greeting(List("cammy"))
  greeting(List("nina","nina"))
  greeting(List("nina","nina","anna"))
  greeting(List("x","y","z"))
  */

  /*
  def matchType(obj:Any): Unit ={
    obj match {
      case x: Int => println(x)
      case x: String => println(x)
      case x: Map[_, _] => println(x)
      case _ => println("other type")
    }
  }

  matchType(1)
  matchType("nina")
  matchType(Map("nana"->"PK"))
  matchType(10f)
  */

  //def caseclassMatch(person:Person): Unit ={
  //  person match{
  //    case CTO(name,floor)=>println("CTO : " + name + " : " + floor)
  //    case Employee(name,floor)=>println("Employee : " + name + " : " + floor)
  //    case _=> println("Others")
  //  }
  //}

  //class Person
  //case class CTO(name:String,floor:String) extends Person
  //case class Employee(name:String,floor:String) extends Person
  //case class Other(name:String) extends Person

  //caseclassMatch(CTO("sisi","floor"))
  //caseclassMatch(Employee("nana","floor"))
  //caseclassMatch(Other("sisi"))

  val grades =Map("Amy"->"A","pp"->"B")

  def getGrade(name:String): Unit ={
    val grade = grades.get(name)

    grade match{
      case Some(grade)=>println(name + " your grade is : " + grade)
      case _ => println("others")
    }
  }

  getGrade("Amy")
  getGrade("oo")

}
