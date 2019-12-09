package _01Functions

object Functions {

  def main(args: Array[String]): Unit = {
    //println(add(1, 2))
    //println(max(1, 2))
    //println(three) // 没有入参括号可省略 build
    //println(three())
    //sayHello()
    //sayHello("sisi")
    //println(sum(1, 2, 3, 4))
    //println(ifesle(-1))
    loops()
  }

  def add(a: Int, b: Int): Int = {
    a + b //  最后一行就是返回值
  }

  def three() = 1 + 2

  def max(a: Int, b: Int): Int = {
    if (a > b)
      a
    else
      b
  }

  def sayHello(content: String = "you mother fucker"): Unit = {
    println("hello " + content)
  }

  // 可变参数
  def sum(numbers: Int*) = {
    var res = 0
    for (number <- numbers) {
      res += number;
    }
    res
  }

  def ifesle(x: Int): Boolean = {
    if (x > 0) true else false
  }

  def loops(): Unit = {
    //println(1.to(10))
    //println(1 to 10) // same as 1.to(10)
    //// until 或 to 很有启发性
    //println(Range(1 ,10)) // Until , [1,10)
    //println(Range(1 ,10 ,2))
    //println(1.until(10))
    //println(1.until(10,2))

    for (i <- 1 to 10){
      print(i +",")
    }
    println()

    for (i <- 1 to 10 if i % 2 ==0){
      print(i +",")
    }
    println()

    val courses = Array("nina","ss","ana")
    //for (course <- courses){
    //  print(course + " ,")
    //}
    courses.foreach(course => print(course + " , "))
    println()
    (1 to 10).foreach(i => if (i %2 ==0)print(i))
    println()

    var (num,sum) =(100,0)
    while(num > 0){
      sum += num;
      num -= 1;
    }
    println(sum)
  }

}
