package w3cschool

object Basic extends App {

  // <-------------------- 变量定义 -------------------->
  // 你可以给一个表达式的结果起个名字赋成一个不变量（val）,你不能改变这个不变量的值.
  val a = 1 + 1
  // 如果你需要修改这个名称和结果的绑定，可以选择使用 var
  var b = 2 + 2

  // scala 中几乎一切都是表达式
  def three() = 1 + 2

  //println(three())

  // <-------------------- 匿名函数 -------------------->
  (x: Int) =>
    x + 1

  // {}来格式化代码
  def timesTwo(i: Int): Int = {
    println("times two")
    i * 2
  }

  // 匿名函数
  {
    i: Int =>
      println("anonymous times two")
      i * 2
  }

  // <-------------------- 部分应用 -------------------->
  /**
   * 部分应用（Partial application）
   * 你可以使用下划线“”部分应用一个函数，结果将得到另一个函数。
   * Scala 使用下划线表示不同上下文中的不同事物，你通常可以把它看作是一个没有命名的神奇通配符。
   * 在`{ + 2 }`的上下文中，它代表一个匿名参数。你可以这样使用它：
   */
  def adder(m: Int, n: Int): Int = {
    m + n
  }

  val add2 = adder(2, _: Int)

  //println(add2(3))

  // <-------------------- curry function -------------------->
  /**
   * 柯里化函数
   * 有时会有这样的需求：允许别人一会在你的函数上应用一些参数，然后又应用另外的一些参数。
   * 例如一个乘法函数，在一个场景需要选择乘数，而另一个场景需要选择被乘数。
   */
  def multiply(m: Int)(n: Int): Int = {
    m * n
  }

  // 你可以直接传入两个参数。
  //println(multiply(2)(3))
  // 你可以填上第一个参数并且部分应用第二个参数。
  val times2 = multiply(2)(_)
  //println(times2(3))

  // 你可以对任何多参数函数执行柯里化。例如之前的 adder 函数
  //println((adder _).curried)

  def capitalizeAll(args: String*): Seq[String] = {
    args.map(arg => arg.capitalize)
    args
  }

  //println(capitalizeAll("rarity", "applejack"))

  // <-------------------- 类 -------------------->
  //class Calculator {
  //  val brand: String = "HP"

  //  def add(m: Int, n: Int): Int = m + n

  //  // 上面的例子展示了如何在类中用 def 定义方法和用 val 定义字段值。方法就是可以访问类的状态的函数。
  //}

  // <-------------------- 构造函数 -------------------->
  // 构造函数不是特殊的方法，他们是除了类的方法定义之外的代码。
  // 让我们扩展计算器的例子，增加一个构造函数参数，并用它来初始化内部状态。

  class Calculator(brand: String) { //constructor
    val color: String =
      if (brand == "TI") {
        "blue"
      } else if (brand == "HP") {
        "black"
      } else {
        "white"
      } // An instance method.  def add(m: Int, n: Int): Int = m + n}

  }

  // 你可以使用构造函数来构造一个实例：
  val calc = new Calculator("HP")
  println(calc.color)

  // <-------------------- 表达式 -------------------->
  // 上文的 Calculator 例子说明了 Scala 是如何面向表达式的。
  // 颜色的值就是绑定在一个if/else表达式上的。

  // <--------------------  Scala 是高度面向表达式的：大多数东西都是表达式而非指令 -------------------->

  // 旁白: 函数 vs 方法
  // 函数和方法在很大程度上是可以互换的。
  // 由于函数和方法是如此的相似，你可能都不知道你调用的东西是一个函数还是一个方法。
  // 而当真正碰到的方法和函数之间的差异的时候，你可能会感到困惑。

  class C {
    var acc = 0

    def minc = {
      acc += 1
    }

    val finc = {
      () => acc += 1
    }
  }

  /**
   * 当你可以调用一个不带括号的“函数”，但是对另一个却必须加上括号的时候，
   * 你可能会想哎呀，我还以为自己知道 Scala 是怎么工作的呢。
   * 也许他们有时需要括号？你可能以为自己用的是函数，但实际使用的是方法。
   *
   * 在实践中，即使不理解方法和函数上的区别，你也可以用 Scala 做伟大的事情。
   * 如果你是 Scala 新手，而且在读两者的差异解释，你可能会跟不上。
   * 不过这并不意味着你在使用 Scala 上有麻烦。
   * 它只是意味着函数和方法之间的差异是很微妙的，只有深入语言内部才能清楚理解它。
   */

  // <-------------------- 继承 -------------------->
  class ScientificCalculator(brand: String) extends Calculator(brand) {
    def log(m: Double, base: Double) = math.log(m) / math.log(base)
  }

  // <-------------------- 重载方法 -------------------->
  class EvenMoreScientificCalculator(brand: String) extends ScientificCalculator(brand) {
    def log(m: Int): Double = log(m, math.exp(1))
  }


  // <-------------------- 抽象类 -------------------->
  // 你可以定义一个抽象类，它定义了一些方法但没有实现它们。
  // 取而代之是由扩展抽象类的子类定义这些方法。你不能创建抽象类的实例。

  abstract class Shape {
    def getArea(): Int
  }

  // <-------------------- 特质(Traits) -------------------->
  // 特质是一些字段和行为的集合，可以扩展或混入（mixin）你的类中。
  trait Car {
    val brand: String
  }

  trait Shiny {
    val shineRefraction: Int
  }

  //class BMW extends Car {
  //  val brand = "BMW"
  //}

  // 通过 with 关键字，一个类可以扩展多个特质：
  class BMW extends Car with Shiny {
    val brand = "BMW"
    val shineRefraction = 12
  }

  val bmw = new BMW()
  println("refraction : " + bmw.shineRefraction) // + bmw.shineRefraction)
  println(bmw.brand)

  // 什么时候应该使用特质而不是抽象类？
  // 如果你想定义一个类似接口的类型，你可能会在特质和抽象类之间难以取舍。
  // 这两种形式都可以让你定义一个类型的一些行为，并要求继承者定义一些其他行为。一些经验法则：
  // 优先使用特质。一个类扩展多个特质是很方便的，但却只能扩展一个抽象类。
  // 如果你需要构造函数参数，使用抽象类。
  // 因为抽象类可以定义带参数的构造函数，而特质不行。
  // 例如，你不能说trait t(i: Int) {}，参数i是非法的。

  // <-------------------- 类型 -------------------->
  // 此前，我们定义了一个函数的参数为 Int，表示输入是一个数字类型。
  // 其实函数也可以是泛型的，来适用于所有类型。
  // 当这种情况发生时，你会看到用方括号语法引入的类型参数。下面的例子展示了一个使用泛型键和值的缓存。

  trait Cache[K, V] {
    def get(key: K): V
    def put(key: K, value: V)
    def delete(key: K)
    // 方法也可以引入类型参数。
    def remove[K](key: K)
  }

}
