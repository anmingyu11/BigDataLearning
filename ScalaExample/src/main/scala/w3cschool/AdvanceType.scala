package w3cschool

object AdvanceType extends App {

  // 视界（“类型类”）
  // 有时候，你并不需要指定一个类型是等/子/超于另一个类，你可以通过转换这个类来伪装这种关联关系。
  // 一个视界指定一个类型可以被“看作是”另一个类型。
  // 这对对象的只读操作是很有用的。
  // 隐函数允许类型自动转换。
  // 更确切地说，在隐式函数可以帮助满足类型推断时，它们允许按需的函数应用。例如：

  // implicit def strToInt(x: String) = x.toInt

  // val y: Int = "123"
  // println(math.max("123", 111))

  // 视界，就像类型边界，要求对给定的类型存在这样一个函数。
  // 您可以使用<%指定类型限制，例如：
  // class Container[A <% Int] {
  //   def addIt(x: A) = 123 + x
  // }

  // 这是说 A 必须“可被视”为 Int 。让我们试试。
  // println(new Container[String].addIt("12"))
  // println((new Container[Int]).addIt(123))
  // println((new Container[Float]).addIt(123.2F))
  // error: could not find implicit value for
  // evidence parameter of type (Float) => Int

  // 其他类型限制
  // 方法可以通过隐含参数执行更复杂的类型限制。
  // 例如，List 支持对数字内容执行 sum，但对其他内容却不行。
  // 可是 Scala 的数字类型并不都共享一个超类，所以我们不能使用T <: Number。
  // 相反，要使之能工作，Scala 的 math 库对适当的类型 T 定义了一个隐含的 Numeric[T]。
  // 然后在 List 定义中使用它：
  // sum[B >: A](implicit num: Numeric[B]): B
  // 如果你调用List(1,2).sum()，你并不需要传入一个 num 参数；它是隐式设置的。
  // 但如果你调用 List("whoop").sum()，它会抱怨无法设置 num。
  // 在没有设定陌生的对象为 Numeric 的时候，方法可能会要求某种特定类型的“证据”。
  // 这时可以使用以下类型-关系运算符：

  // 在没有设定陌生的对象为 Numeric 的时候，方法可能会要求某种特定类型的“证据”。
  // 这时可以使用以下类型-关系运算符：
  // A =:= B A 必须和 B 相等
  // A <:< B A 必须是 B 的子类
  // A <%< B A 必须可以被看做是 B

  //class Container[A](value: A) {
  //  def addIt(implicit evidence: A =:= Int) = {
  //    123 + value
  //  }
  //}

  // println((new Container(123)).addIt)
  // (new Container("123")).addIt
  // error: could not find implicit value
  // for parameter evidence: =:=[java.lang.String,Int]

  // 类似地，根据之前的隐式转换，我们可以放松约束为可视性：
  //class Container[A](value: A) {
  // def addIt(implicit evidence: A <%< Int) = 123 + value
  // could not do that <%<
  // 这个版本不能用?
  //}

  // 使用视图进行泛型编程
  // 在 Scala 标准库中，视图主要用于实现集合的通用函数。
  // 例如“min”函数（在 Seq[] 上）就使用了这种技术：

  // def min[B >: A](implicit cmp: Ordering[B]): A = {
  //   if (isEmpty)
  //     throw new UnsupportedOperationException("empty.min")

  //   reduceLeft((x, y) => if (cmp.lteq(x, y)) x else y)
  // }

  // 其主要优点是：
  // - 集合中的元素并不是必须实现 Ordered 特质 , 但 Ordered 的使用仍然可以执行静态类型检查。
  // - 无需任何额外的库支持 , 你也可以定义自己的排序：
  println(List(2, 1, 3, 4).min)
  println(List(1, 2, 3, 4).min(
    new Ordering[Int] {
      override def compare(x: Int, y: Int): Int = y compare x
    }
  ))

  // 作为旁注，标准库中有视图来将 Ordered 转换为 Ordering （反之亦然）。
  // trait LowPriorityOrderingImplicits {
  // implicit def ordered[A <: Ordered[A]]: Ordering[A] = new Ordering[A] {
  // def compare(x: A, y: A) = x.compare(y)
  // }
  // }


  // 上下文边界和implicitly[]
  // Scala2.8 引入了一种串联和访问隐式参数的快捷方式。


}
