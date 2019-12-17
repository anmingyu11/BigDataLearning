package w3cschool

object Match_Function extends App {

  // 模式匹配与函数组合
  // 函数组合
  // 让我们创建两个函数：
  def f(s: String) = "f(" + s + ")"

  def g(s: String) = "g(" + s + ")"

  // <-------------------- compose -------------------->
  // compose 组合其他函数形成一个新的函数 f(g(x))
  // val fComposeG = f _ compose g _
  // println(fComposeG("yaye"))

  // <-------------------- andThen -------------------->
  // andThen 和 compose很像，但是调用顺序是先调用第一个函数，然后调用第二个，即g(f(x))
  // val fAndThenG = f _ andThen g _
  // println(fAndThenG("an"))

  // 柯里化 vs 偏应用
  // case 语句
  // 那么究竟什么是 case 语句？
  // **这是一个名为 PartialFunction 的函数的子类。**
  // 多个 case 语句的集合是什么？
  // 他们是共同组合在一起的多个 PartialFunction。

  // ###################### 理解 PartialFunction(偏函数) ######################
  // 对给定的输入参数类型，函数可接受该类型的任何值。
  // 换句话说，一个(Int) => String的函数可以接收任意 Int 值，并返回一个字符串。
  // 对给定的输入参数类型 , 偏函数只能接受该类型的某些特定的值。
  // 一个定义为(Int) => String 的偏函数可能不能接受所有 Int 值为输入。
  // isDefinedAt 是 PartialFunction 的一个方法，用来确定 PartialFunction 是否能接受一个给定的参数。
  // 注意：偏函数 PartialFunction 和我们前面提到的部分应用函数是无关的。

  val one: PartialFunction[Int, String] = {
    case 1 => "one"
    //case _ => "other"
  }
  // println(one.isDefinedAt(1))
  // println(one.isDefinedAt(2))
  //one(2)

  // PartialFunctions 可以使用 orElse 组成新的函数，
  // 得到的 PartialFunction 反映了是否对给定参数进行了定义。

  val two: PartialFunction[Int, String] = {
    case 2 => "two"
  }
  val three: PartialFunction[Int, String] = {
    case 3 => "two"
  }
  val wildcard: PartialFunction[Int, String] = {
    case _ => "others"
  }
  val partial = one orElse two orElse three orElse wildcard

  // println(partial(2))
  // println(partial(10))

  // case 之谜
  // 上周我们看到一些新奇的东西。我们在通常应该使用函数的地方看到了一个 case 语句。
  case class PhoneExt(name: String, ext: Int)

  val extensions = List(PhoneExt("steve", 100), PhoneExt("robey", 200))
  extensions.filter {
    case PhoneExt(name, extension) => extension < 200
  }
  // 为什么这段代码可以工作？
  // filter 使用一个函数。在这个例子中是一个谓词函数(PhoneExt) => Boolean。
  // PartialFunction 是 Function 的子类型，所以 filter 也可以使用 PartialFunction！

}
