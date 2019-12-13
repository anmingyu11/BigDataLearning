package _03Collections

object ListApp {
  def main(args: Array[String]): Unit = {
    //val l1 = List(1,2,3,4)
    //println(l1)
    //println(l1.head)
    //println(l1.tail)
    //val l2 = 1::Nil
    //println(l2)
    //val l3 = 1 :: 4::5::Nil
    //println(l3)
    //val l4 = l2::l1
    //println(l4)

    val l = collection.mutable.ListBuffer(1, 2, 3, 4, 5)
    l += 2
    l += 3
    l += (1, 2, 3)
    l ++= List(3, 4, 5)
    println(l)
    l -= 1
    l -= 3
    l -= 5
    l -= 5
    println(l)

    println(l.isEmpty)
    println(l.head)
    println(l.tail)


  }
}
