object _07seniorType {
  // 高级类型
  // 视界（“类型类”）
  // 有时候，你并不需要指定一个类型是等/子/超于另一个类，你可以通过转换这个类来伪装这种关联关系。
  // 一个视界指定一个类型可以被“看作是”另一个类型。这对对象的只读操作是很有用的。
  //
  // 隐函数允许类型自动转换。更确切地说，在隐式函数可以帮助满足类型推断时，它们允许按需的函数应用。例如：

  implicit def strToInt(x: String) = x.toInt

  val y: Int = "123"

  println(math.max("123123", 12313))

  // 视界，就像类型边界，要求对给定的类型存在这样一个函数。您可以使用<%指定类型限制，例如：
  // View bounds are deprecated
  class Container[A <% Int] {
    def addInt(x: A) = 123 + x
  }

  // 这是说 A 必须“可被视”为 Int 。让我们试试。
  (new Container[String]).addInt("123")
  (new Container[Int]).addInt(123)

  // 其他类型限制
  // 方法可以通过隐含参数执行更复杂的类型限制。
  // 例如，List 支持对数字内容执行 sum，但对其他内容却不行。
  // 可是 Scala 的数字类型并不都共享一个超类，所以我们不能使用T <: Number。
  // 相反，要使之能工作，Scala 的 math 库对适当的类型 T 定义了一个隐含的
  // Numeric[T]。 然后在 List 定义中使用它：
  
  def main(args: Array[String]): Unit = {

  }
}
