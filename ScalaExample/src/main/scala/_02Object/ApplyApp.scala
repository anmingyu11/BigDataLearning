package _02Object

object ApplyApp{
  def main(args: Array[String]): Unit = {
    //for (i <- 1 to 10){
    //  ApplyTest.increet
    //}
    //println(ApplyTest.count)
    val b = ApplyTest()// 伴生对象 Object => apply
    println("=========")
    val c = new ApplyTest()
    println(c)
  }
}

/**
 * 伴生类和伴生对象
 * class a object a
 * object 是a的伴生对象
 * class 是a的伴生类
 */
class ApplyTest {

}
object ApplyTest{
  println("in apply test")
  var count = 0

  def increet ={
    count +=1
  }

  def apply()={
    println("object ApplyTest apply")
    new ApplyTest
  }


  println("out apply test")

}
