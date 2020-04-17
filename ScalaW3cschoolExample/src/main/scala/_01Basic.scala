object _01Basic {

  class Calculator(val brand: String) {
    val color: String = if (brand == "ti") {
      "blue"
    } else if (brand == "hp") {
      "black"
    } else {
      "white"
    }
  }

  class C {
    var acc = 0

    def minc = {
      acc += 1
    }

    val finc = {
      () => acc += 1
    }

  }

  class ScientificCalculator(brand: String) extends Calculator(brand) {
    def log(m: Double, base: Double) = math.log(m) / math.log(base)
  }

  abstract class Shape {
    def getArea(): Int
  }

  trait Car {
    val brand: String
  }

  trait Shiny {
    val shineRefraction: Int
  }

  // 特质（Traits）
  // 特质是一些字段和行为的集合，可以扩展或混入（mixin）你的类中。
  class BMW extends Car with Shiny {
    val brand = "BMW"
    override val shineRefraction: Int = 12
  }

  // 什么时候应该使用特质而不是抽象类？
  // 如果你想定义一个类似接口的类型，你可能会在特质和抽象类之间难以取舍。
  // 这两种形式都可以让你定义一个类型的一些行为，并要求继承者定义一些其他行为。
  // 一些经验法则：

  // 优先使用特质。一个类扩展多个特质是很方便的，但却只能扩展一个抽象类。
  // 如果你需要构造函数参数，使用抽象类。因为抽象类可以定义带参数的构造函数，而特质不行。
  // 例如，你不能说trait t(i: Int) {}，参数i是非法的。类型
  // 此前，我们定义了一个函数的参数为 Int，表示输入是一个数字类型。
  // 其实函数也可以是泛型的，来适用于所有类型。当这种情况发生时，你会看到用方括号语法引入的类型参数。
  // 下面的例子展示了一个使用泛型键和值的缓存。

  // 类型
  // 此前，我们定义了一个函数的参数为 Int，表示输入是一个数字类型。
  // 其实函数也可以是泛型的，来适用于所有类型。
  // 当这种情况发生时，你会看到用方括号语法引入的类型参数。
  // 下面的例子展示了一个使用泛型键和值的缓存。

  trait Cache[K, V] {
    def get(key: K): V
    def put(key: K, value: V)
    def delete(key: K)
  }

  def main(args: Array[String]): Unit = {
    val cal = new Calculator("hp")
    print(cal.color)
    val c = new C
    print(c.minc)
  }

}
