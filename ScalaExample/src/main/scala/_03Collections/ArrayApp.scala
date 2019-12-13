object ArrayApp extends App {
  //val a = new Array[String](5);
  //println(a)
  //println(a.length)
  //a(1)="hello";
  //val b = Array("ss","sss","ssss")// Apply 方法new 出来的
  //println(b(1))
  //val c = Array(1,2,3,4,5,6)
  //println(c.sum)
  //println(c.max)
  //println(c.min)
  //println(c.mkString)
  //println(c.mkString(":"))

  val mA = collection.mutable.ArrayBuffer[Int]()

  mA += 1
  mA += 2
  mA += 3
  mA += (4, 5, 6, 7)
  mA ++= Array(6, 7, 9)
  //mA.insert(1,2)
  //println(mA)
  //mA.remove(8)
  //mA.remove(1, 3)
  //println(mA)
  //mA.trimEnd(3)
  //println(mA)
  //println(mA.toArray.mkString(","))

  for (i <- 0 until mA.length){
    print(mA(i) + " , ")
  }

  println()
  for (ele <- mA){
    print(ele + " , ")
  }
  println()

  mA.foreach(x => print(x*x + " , "))
  println

}
