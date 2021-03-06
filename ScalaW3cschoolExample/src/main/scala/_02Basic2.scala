
object _02Basic2 {

  // apply 方法
  // 当类或对象有一个主要用途的时候，apply 方法为你提供了一个很好的语法糖。
  class Foo {}

  object FooMaker {
    def apply() = new Foo
  }

  val newFoo = FooMaker()
  val newFoo2 = FooMaker()

  println(newFoo)
  println(newFoo2)

  //class Bar {
  //def apply() = 0
  //}

  //val bar = new Bar

  //println(bar())

  // 在这里，我们实例化对象看起来像是在调用一个方法。以后会有更多介绍！

  // 单例对象
  // 单例对象用于持有一个类的唯一实例。
  // 通常用于工厂模式。

  object Timer {
    var count = 0

    def currentCount(): Long = {
      count += 1
      count
    }
  }

  println(Timer.currentCount())

  // 单例对象可以和类具有相同的名称，此时该对象也被称为“伴生对象”。
  // 我们通常将伴生对象作为工厂使用。
  // 下面是一个简单的例子，可以不需要使用new来创建一个实例了。

  class Bar(foo: String)

  object Bar {
    def apply(foo: String) = new Bar(foo)
  }

  // 函数即对象
  // 在 Scala 中，我们经常谈论对象的函数式编程。这是什么意思？到底什么是函数呢？

  // 函数是一些特质的集合。
  // 具体来说，具有一个参数的函数是 Function1 特质的一个实例。
  // 这个特征定义了 apply()语法糖，让你调用一个对象时就像你在调用一个函数。

  object addOne extends Function1[Int, Int] {
    def apply(m: Int): Int = m + 1
  }

  println(addOne(5))

  // 这个 Function 特质集合下标从 0 开始一直到 22。
  // 为什么是 22？这是一个主观的魔幻数字(magic number)。我从来没有使用过多于 22 个参数的函数，所以这个数字似乎是合理的。
  // apply 语法糖有助于统一对象和函数式编程的二重性。你可以传递类，并把它们当做函数使用，而函数本质上是类的实例。
  // 这是否意味着，当你在类中定义一个方法时，得到的实际上是一个 Function* 的实例？
  // 不是的，在类中定义的方法是方法而不是函数。
  // 在 repl 中独立定义的方法是 Function* 的实例。
  // 类也可以扩展 Function，这些类的实例可以使用()调用。

  //class AddOne extends Function1[Int, Int] {
  //def apply(v1: Int): Int = v1 + 1
  //}
  // val plusOne = new AddOne()
  //可以使用更直观快捷的 extends (Int => Int) 代替 extends Function1[Int, Int]

  // plusOne(1)

  // 可以使用更直观快捷的 extends (Int => Int) 代替 extends Function1[Int, Int]

  class AddOne extends (Int => Int) {
    def apply(v1: Int): Int = v1 + 1
  }

  // 包
  // 你可以将代码组织在包里。

  // 在文件头部定义包，会将文件中所有的代码声明在那个包中。
  // 值和函数不能在类或单例对象之外定义。
  // 单例对象是组织静态函数(static function)的有效工具。

  object colorHolder {
    val BLUE = "blue"
    val RED = "red"
  }

  // 现在你可以直接访问这些成员


  // 注意在你定义这个对象时 Scala 解释器的返回：

  // scala> object colorHolder {
  // |   val Blue = "Blue"
  // |   val Red = "Red"
  // | }
  // defined module colorHolder
  // 这暗示了 Scala 的设计者是把对象作为 Scala 的模块系统的一部分进行设计的。

  // 模式匹配
  // 这是 Scala 中最有用的部分之一。

  // 匹配值

  val times = 1

  times match {
    case 1 => "one"
    case 2 => "two"
    case _ => "some other number"
  }

  // 使用守卫进行匹配
  times match {
    case i if i == 1 => "one"
    case i if i == 2 => "two"
    case _ => "some other number"
  }

  // 注意我们是怎样将值赋给变量i的。
  // 在最后一行指令中的_是一个通配符；它保证了我们可以处理所有的情况。
  // 否则当传进一个不能被匹配的数字的时候，你将获得一个运行时错误。我们以后会继续讨论这个话题的。

  // 匹配类型
  // 你可以使用 match 来分别处理不同类型的值。
  def bigger(o: Any): Any = {
    o match {
      case i: Int if i < 0 => i - 1
      case i: Int => i + 1
      case d: Double if d < 0.0 => d - 0.1
      case d: Double => d + 0.1
      case text: String => text + "s"
    }
  }

  // 样本类 Case Classes
  // 使用样本类可以方便得存储和匹配类的内容。
  // 你不用 new 关键字就可以创建它们。
  case class Calculator(brand: String, model: String)

  // 匹配类成员
  // 还记得我们之前的计算器吗。
  // 让我们通过类型对它们进行分类。
  def calcType(calc: Calculator) = calc match {
    case _ if calc.brand == "hp" && calc.model == "20" => "financial"
    case _ if calc.brand == "hp" && calc.model == "30" => "scientific"
    case _ if calc.brand == "hp" && calc.model == "40" => "bussiness"
    case _ => "unknown"
  }

  // 样本类基于构造函数的参数，自动地实现了相等性和易读的 toString 方法。
  val hp20 = Calculator("hp", "20")
  val hp20_2 = Calculator("hp", "20")
  print(hp20)
  print(hp20 == hp20_2)
  // 样本类也可以像普通类那样拥有方法。

  // 使用样本类进行模式匹配
  // 样本类就是被设计用在模式匹配中的。让我们简化之前的计算器分类器的例子。

  // Scala 中的异常可以在 try-catch-finally 语法中通过模式匹配使用。

  def main(args: Array[String]): Unit = {
  }

}
