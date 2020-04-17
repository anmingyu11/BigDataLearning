object _06TypeAndPoly____11 {

  // 类型和多态基础
  // 类型和多态基础
  // 什么是静态类型？
  // 按 Pierce 的话讲：“类型系统是一个语法方法，它们根据程序计算的值的种类对程序短语进行分类，通过分类结果错误行为进行自动检查。”
  // 类型允许你表示函数的定义域和值域。例如，从数学角度看这个定义：

  // f: R -> N
  // 它告诉我们函数“f”是从实数集到自然数集的映射。
  // 抽象地说，这就是具体类型的准确定义。类型系统给我们提供了一些更强大的方式来表达这些集合。
  // 鉴于这些注释，编译器可以静态地 （在编译时）验证程序是合理的。也就是说，如果值（在运行时）不符合程序规定的约束，编译将失败。
  // 一般说来，类型检查只能保证不合理的程序不能编译通过。它不能保证每一个合理的程序都可以编译通过。
  // 随着类型系统表达能力的提高，我们可以生产更可靠的代码，因为它能够在我们运行程序之前验证程序的不变性（当然是发现类型本身的模型 bug！）。
  // 学术界一直很努力地提高类型系统的表现力，包括值依赖（value-dependent）类型！
  // **需要注意的是，所有的类型信息会在编译时被删去，因为它已不再需要。这就是所谓的擦除。

  // Scala 中的类型
  // Scala 强大的类型系统拥有非常丰富的表现力。其主要特性有：
  // - 参数化多态性 粗略地说，就是泛型编程
  // -（局部）类型推断粗略地说，就是为什么你不需要这样写代码 val i: Int = 12: Int
  // - 存在量化 粗略地说，为一些没有名称的类型进行定义
  // - 视窗 我们将下周学习这些；粗略地说，就是将一种类型的值“强制转换”为另一种类型

  // 参数化多态性
  // 多态性是在不影响静态类型丰富性的前提下，用来（给不同类型的值）编写通用代码的。
  // 例如，如果没有参数化多态性，一个通用的列表数据结构总是看起来像这样（事实上，它看起来很像使用泛型前的Java）：

  val l1 = 2 :: 1 :: "bar" :: "foo" :: Nil
  println(l1)

  // 现在我们无法恢复其中成员的任何类型信息。

  // 所以我们的应用程序将会退化为一系列类型转换（“asInstanceOf[]”），并且会缺乏类型安全的保障（因为这些都是动态的）。
  // 多态性是通过指定 类型变量 实现的。

  def drop1[A](l: List[A]) = l.tail

  println(drop1(l1))

  // Scala 有秩 1 多态性
  // 粗略地说，这意味着在 Scala 中，有一些你想表达的类型概念“过于泛化”以至于编译器无法理解。假设你有一个函数
  def toList[A](a: A) = List(a)

  // 你希望继续泛型地使用它:
  // def foo[A, B](f: A => List[A], b: Int) = f(b)

  // 这段代码不能编译，因为所有的类型变量只有在调用上下文中才被固定。即使你“钉住”了类型 B：
  // …你也会得到一个类型不匹配的错误。

  // 类型推断
  // 静态类型的一个传统反对意见是，它有大量的语法开销。
  // Scala 通过 类型推断 来缓解这个问题。
  // 在函数式编程语言中，类型推断的经典方法是 Hindley Milner 算法，它最早是实现在 ML 中的。
  // Scala 类型推断系统的实现稍有不同，但本质类似：推断约束，并试图统一类型。
  // 例如，在 Scala 中你无法这样做：


  // 变性 Variance
  // Scala 的类型系统必须同时解释类层次和多态性。
  // 类层次结构可以表达子类关系。
  // 在混合 OO 和多态性时，一个核心问题是：如果 T’ 是 T 一个子类，Container[T’]应该被看做是 Container[T]
  // 的子类吗？变性（Variance）注解允许你表达类层次结构和多态类型之间的关系：

  // 名称	            含义	Scala 标记
  // 协变covariant	    C[T’]是 C[T] 的子类	[+T]
  // 逆变contravariant	C[T] 是 C[T’]的子类	[-T]
  // 不变invariant	C[T] 和 C[T’]无关	[T]
  // 子类型关系的真正含义：对一个给定的类型T，如果T’是其子类型，你能替换它吗？
  trait Function1[-T1, +R] extends AnyRef

  class Animal {
    val sound = "rustle"
  }

  class Bird extends Animal {
    override val sound = "call"
  }

  class Chicken extends Bird {
    override val sound = "cluck"
  }

  // 假设你需要一个以 Bird 为参数的函数：
  // val getTweet:(Bird => String) = // Todo

  // 标准动物库有一个函数满足了你的需求，但它的参数是 Animal。
  // 在大多数情况下，如果你说 “我需要一个___，我有一个___的子类” 是可以的。
  // 但是，在函数参数这里是逆变的。
  // 如果你需要一个接受参数类型 Bird 的函数变量，但却将这个变量指向了接受参数类型为 Chicken 的函数，
  // 那么给它传入一个 Duck 时就会出错。然而，如果将该变量指向一个接受参数类型为 Animal 的函数就不会有这种问题：
  val getTweet: (Bird => String) = ((a: Animal) => a.sound)

  // 函数的返回值类型是协变的。如果你需要一个返回 Bird 的函数，但指向的函数返回类型是 Chicken，这当然是可以的。
  val hatch: (() => Bird) = (() => new Chicken)

  // 边界
  // Scala 允许你通过边界来限制多态变量。这些边界表达了子类型关系。
  // 类型下界也是支持的，这让逆变和巧妙协变的引入得心应手。
  // List[+T] 是协变的；一个 Bird 的列表也是 Animal 的列表。
  // List 定义一个操作::(elem T)返回一个加入了 elem 的新的 List。
  // 新的 List 和原来的列表具有相同的类型：

  // List 同样定义了::[B >: T](x: B) 来返回一个List[B]。
  // 请注意B >: T，这指明了类型B为类型T的超类。
  // 这个方法让我们能够做正确地处理在一个 List[Bird]前面加一个 Animal 的操作：

  def main(args: Array[String]): Unit = {
  }

}