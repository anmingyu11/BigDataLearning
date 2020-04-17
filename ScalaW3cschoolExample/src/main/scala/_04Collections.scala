object _04Collections {

  // 集合
  // 集合
  // 基本数据结构
  // Scala 提供了一些不错的集合。

  // 列表 List
  // val numbers = List(1, 2, 3, 4)
  // println(numbers)

  // 集 Set
  println(Set(1, 1, 2))

  // 元组 Tuple
  // 元组是在不使用类的前提下，将元素组合起来形成简单的逻辑集合。
  val hostPort = ("localhost", 80)
  println(hostPort)

  // 与样本类不同，元组不能通过名称获取字段，而是使用位置下标来读取对象；
  // 而且这个下标基于 1，而不是基于 0。
  println(hostPort._1, hostPort._2)

  // 元祖可以很好的与模式匹配相结合
  //hostPort match{
  //case ("localhost",port) => ...
  //case (host,port) =>  ...
  //}

  // 映射 Map
  // 它可以持有基本数据类型。
  Map(1 -> 2)
  Map("foo" -> "bar")

  // 这看起来像是特殊的语法，不过不要忘了上文讨论的 -> 可以用来创建二元组。
  // Map()方法也使用了从第一节课学到的变参列表：Map(1 -> "one", 2 -> "two")将变为 Map((1, "one"), (2, "two"))，
  // 其中第一个参数是映射的键，第二个参数是映射的值。
  // 映射的值可以是映射甚或是函数。
  Map(1 -> Map("foo" -> "bar"))

  // 选项 Option
  // Option 是一个表示有可能包含值的容器。
  // Option 基本的接口是这样的：
  trait Option[T] {
    def isDefined: Boolean

    def get: T

    def getOrElse(t: T): T
  }

  // Option 本身是泛型的，并且有两个子类： Some[T] 或 None

  // 我们看一个使用 Option 的例子：
  // Map.get 使用 Option 作为其返回值，表示这个方法也许不会返回你请求的值。
  val numbers = Map("one" -> 1, "two" -> 2)
  println(numbers)

  println(numbers.get("two"))
  println(numbers.get("one"))

  // 现在我们的数据似乎陷在 Option 中了，我们怎样获取这个数据呢？
  // 直觉上想到的可能是在 isDefined 方法上使用条件判断来处理。
  // 我们建议使用 getOrElse 或模式匹配处理这个结果。
  // getOrElse 让你轻松地定义一个默认值。
  val result = numbers.getOrElse("three", 3)
  println(result)

  // 模式匹配能自然地配合 Option 使用。

  // 函数组合子
  // List(1, 2, 3) map squared 对列表中的每一个元素都应用了squared 平方函数，并返回一个新的列表 List(1, 4, 9)。
  // 我们称这个操作 map 组合子。
  // （如果想要更好的定义，你可能会喜欢 Stackoverflow 上对组合子的说明。）他们常被用在标准的数据结构上。

  // map
  // map 对列表中的每个元素应用一个函数，返回应用后的元素所组成的列表。
  var ns = List(2, 4, 6, 8)
  println(ns.map(i => i * 2))

  // 或传入一个部分应用函数
  def timesTwo(i: Int): Int = i * 2

  println(ns.map(timesTwo _))

  // foreach
  // foreach 很像 map，但没有返回值。foreach 仅用于有副作用[side-effects]的函数。
  println(ns.foreach(i => println(i * 2)))

  // 什么也没有返回。
  // 你可以尝试存储返回值，但它会是 Unit 类型（即void）

  // filter
  // filter 移除任何对传入函数计算结果为 false 的元素。返回一个布尔值的函数通常被称为谓词函数[或判定函数]。
  println(ns.filter(i => i % 2 == 0))

  def isEven(i: Int): Boolean = i > 2

  println(ns.filter(isEven))

  // zip
  // zip 将两个列表的内容聚合到一个对偶列表中。
  println(List(1, 2, 3).zip(List("a", "b", "c")))

  // partition
  // partition 将使用给定的谓词函数分割列表
  val ns2 = List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
  println(ns2.partition(_ % 2 == 0))

  // find
  // find返回集合中第一个匹配谓词函数的元素
  println(ns2.find(i => i > 5))

  // drop & dropWhile
  // drop 将删除前i个元素
  println(ns2.drop(5))

  // dropWhile 将删除元素直到找到第一个匹配谓词函数的元素。
  // 例如，如果我们在 numbers 列表上使用 dropWhile 奇数的函数, 1 将被丢弃（但 3 不会被丢弃，因为他被 2 “保护”了）。
  println(ns2.dropWhile(_ % 2 != 0))

  // foldLeft
  // 0 为初始值（记住 numbers 是 List[Int] 类型），m 作为一个累加器。
  //
  //直接观察运行过程：
  println(ns2.foldLeft(0) { (m: Int, n: Int) => println(m + ":" + n); m + n })

  // flatten
  // flatten 将嵌套结构扁平化为一个层次的集合。
  println(List(List(1, 2), List(3, 4)).flatten)

  // flatMap
  // flatMap 是一种常用的组合子，结合映射 [mapping] 和扁平化 [flattening]。
  // flatMap 需要一个处理嵌套列表的函数，然后将结果串连起来。
  val nestedNumbers = List(List(1, 2), List(3, 4))
  println(nestedNumbers.flatMap(x => x.map(_ * 2)))

  println(nestedNumbers.map(x => x.map(_ * 3)).flatten)
  // 这个例子先调用 map，然后可以马上调用 flatten，这就是“组合子”的特征，也是这些函数的本质。

  // 扩展函数组合子
  // 现在我们已经学过集合上的一些函数。
  // 我们将尝试写自己的函数组合子。
  // 有趣的是，上面所展示的每一个函数组合子都可以用 fold 方法实现。让我们看一些例子。

  def main(args: Array[String]): Unit = {

  }

}
