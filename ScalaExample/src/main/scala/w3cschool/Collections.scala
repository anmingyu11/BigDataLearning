package w3cschool

import scala.collection.mutable

object Collections extends App {
  // <-------------------- 集合 -------------------->
  // 基本数据结构
  // Scala 提供了一些不错的集合。

  // 列表 List
  // val number_ex= List(1,2,3,4,5)

  // 集 Set
  // 集没有重复
  // val set_ex = Set(1,1,2)

  // <-------------------- 元组 Tuple -------------------->
  // 元组是在不使用类的前提下，将元素组合起来形成简单的逻辑集合。
  // val hostPort = ("localhost", 80)
  // 与样本类不同，元组不能通过名称获取字段，而是使用位置下标来读取对象；
  // ** 而且这个下标基于 1，而不是基于 0。**
  // println(hostPort._1 + " : " + hostPort._2)

  // 元组可以很好得与模式匹配相结合。
  // hostPort match {
  // case ("localhost", port) => ...
  // case (host, port) => ...
  // }

  // 在创建两个元素的元组时，可以使用特殊语法：->

  // println(hostPort)
  // println(1->2)

  // <-------------------- Map -------------------->
  // 它可以持有基本数据类型。
  // Map(1 -> 2)
  // Map("foo" -> "bar")
  // 这看起来像是特殊的语法，**不过不要忘了上文讨论的->可以用来创建二元组。**

  // Map()方法也使用了从第一节课学到的变参列表：
  // Map(1 -> "one", 2 -> "two")
  // 将变为
  // Map((1, "one"), (2, "two"))，
  // 其中第一个参数是映射的键，第二个参数是映射的值。
  // 映射的值可以是映射甚或是函数。
  // Map(1 -> Map("foo" -> "bar"))
  // Map("timesTwo" -> { timesTwo(_) })

  // <-------------------- Option -------------------->
  // Option 是一个表示有可能包含值的容器。
  // Option基本的接口是这样的：
  // trait Option[T] {
  //   def isDefined: Boolean
  //   def get: T
  //   def getOrElse(t: T): T
  // }

  // Option 本身是泛型的，并且有两个子类： Some[T] 或 None
  // 我们看一个使用 Option 的例子：
  // Map.get 使用 Option 作为其返回值，表示这个方法也许不会返回你请求的值。
  // val numbers = Map("one" -> 1, "two" -> 2)
  // println(numbers.get("one"))
  // println(numbers.get("two"))

  // 现在我们的数据似乎陷在 Option 中了，我们怎样获取这个数据呢？
  // 直觉上想到的可能是在 isDefined 方法上使用条件判断来处理。
  // We want to multiply the number by two, otherwise return 0.
  // val res = numbers.get("two")
  // val result = if (res.isDefined) {
  //   res.get * 2
  // } else {
  //   0
  // }
  // println(result)

  // 我们建议使用 getOrElse 或模式匹配处理这个结果。
  // getOrElse 让你轻松地定义一个默认值。
  // println(numbers.getOrElse("three", 3) * 2)
  // println(numbers)

  // 模式匹配能自然地配合 Option 使用。
  // val result = res1 match {
  //  case Some(n) => n * 2
  //  case None => 0
  // }

  // <-------------------- 函数组合子 -------------------->
  // List(1, 2, 3) map squared 对列表中的每一个元素都应用了squared 平方函数，
  // 并返回一个新的列表 List(1, 4, 9)。
  // 我们称这个操作 map 组合子。
  // （如果想要更好的定义，你可能会喜欢 Stackoverflow 上对组合子的说明。）
  // 他们常被用在标准的数据结构上。

  //<-------------------- map -------------------->
  // map 对列表中的每个元素应用一个函数，返回应用后的元素所组成的列表。
  // val numbers = List(1, 2, 3, 4)
  // println(numbers.map((e: Int) => e * 2))
  // println(numbers)

  //<-------------------- foreach -------------------->
  // foreach 很像 map，但没有返回值。foreach 仅用于有副作用[side-effects]的函数。
  // numbers.foreach(i => i * 2)
  // 什么也没有返回。
  // 你可以尝试存储返回值，但它会是 Unit 类型（即void）
  // val numbers = List(1, 2, 3, 4)
  // val doubled = numbers.foreach((i: Int) => i * 2)
  // println(doubled)

  //<-------------------- filter -------------------->
  // filter 移除任何对传入函数计算结果为 false 的元素。
  // 返回一个布尔值的函数通常被称为谓词函数[或判定函数]。
  // val numbers = List(1, 2, 3, 4)
  // println(numbers.filter(x => x % 2 == 0))

  // def isOdd(x: Int): Boolean = {
  // x % 2 == 1
  // }
  // println(numbers.filter(isOdd _))

  // <-------------------- zip -------------------->
  // zip 将两个列表的内容聚合到一个对偶列表中。
  // println(List(1, 2, 3).zip(List("a", "b", "c")))

  // <-------------------- partition -------------------->
  // partition 将使用给定的谓词函数分割列表。
  // val numbers = List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
  // println(numbers.partition(x => (x & 1) == 0))

  // <-------------------- find -------------------->
  // find 返回集合中**第一个**匹配谓词函数的元素。
  // val numbers = List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
  // println(numbers.find((i: Int) => i > 5))

  // <-------------------- drop & dropWhile -------------------->
  // drop 将删除前 i 个元素
  // val numbers = List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
  // println(numbers.drop(5))

  // dropWhile 将删除元素直到找到第一个匹配谓词函数的元素。
  // 例如，如果我们在 numbers 列表上使用 dropWhile 奇数的函数,
  // 1 将被丢弃（但 3 不会被丢弃，因为他被 2 “保护”了）。
  //println(numbers.dropWhile(_ % 5 != 0))

  // <-------------------- foldLeft -------------------->
  // println(numbers.foldLeft(0)((m: Int, n: Int) => m + n))
  // 0 为初始值（记住 numbers 是 List[Int] 类型），m 作为一个累加器。
  //
  // 直接观察运行过程：
  // 0 为初始值（记住 numbers 是 List[Int] 类型），m 作为一个累加器。
  // numbers.foldLeft(2)(
  //   (m: Int, n: Int) => {
  //     println("m : " + m + " n : " + n)
  //     m + n
  //   }
  // )
  // foldRight
  // 和 foldLeft 一样，只是运行过程相反。
  // numbers.foldRight(0) { (m: Int, n: Int) => println("m: " + m + " n: " + n); m + n }

  // <-------------------- flatten -------------------->
  // flatten 将嵌套结构扁平化为一个层次的集合。
  // println(List(List(1, 2), List(3, 4)).flatten)

  // <-------------------- flatMap -------------------->
  // flatMap 是一种常用的组合子，结合映射 [mapping] 和扁平化 [flattening]。
  // flatMap 需要一个处理嵌套列表的函数，然后将结果串连起来。
  // val nestedNumbers = List(List(1, 2), List(3, 4))
  // println(nestedNumbers.flatMap(x => x.map(_ * 2)))
  // **可以把它看做是“先映射后扁平化”的快捷操作：**
  // nestedNumbers.map((x: List[Int]) => x.map(_ * 2)).flatten
  // 这个例子先调用 map，然后可以马上调用 flatten，这就是“组合子”的特征，也是这些函数的本质。

  // <-------------------- 扩展函数组合因子 -------------------->
  // 现在我们已经学过集合上的一些函数。
  // 我们将尝试写自己的函数组合子。
  // 有趣的是，上面所展示的每一个函数组合子都可以用 fold 方法实现。
  // 让我们看一些例子。
  // def ourMap(numbers: List[Int], fn: Int => Int): List[Int] = {
  //  numbers.foldRight(List[Int]()) {
  //  (x: Int, xs: List[Int]) =>
  //      fn(x) :: xs
  //  }
  // }
  // ourMap(numbers, timesTwo(_))
  // List[Int] = List(2, 4, 6, 8, 10, 12, 14, 16, 18, 20)

  // <-------------------- Map? -------------------->
  // 所有展示的函数组合子都可以在 Map 上使用。
  // Map 可以被看作是一个二元组的列表，所以你写的函数要处理一个键和值的二元组。
  val extensions = Map("steve" -> 100, "bob" -> 101, "joe" -> 201)
  // 现在筛选出电话分机号码低于 200 的条目。
  // extensions.filter((tup: (String, Int)) => tup._2 < 200)
  // 因为参数是元组，所以你必须使用位置获取器来读取它们的键和值。
  // 幸运的是，我们其实可以使用模式匹配更优雅地提取键和值。
  println(extensions.filter({ case (k, v) => v < 200 }))

}