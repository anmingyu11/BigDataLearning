package w3cschool

object Class_Polynomial extends App {
  // 类型和多态基础
  // 什么是静态类型？
  // 按 Pierce 的话讲：
  // “类型系统是一个语法方法，
  // 它们根据程序计算的值的种类对程序短语进行分类，通过分类结果错误行为进行自动检查。”
  // 类型允许你表示函数的定义域和值域。例如，从数学角度看这个定义：

  // f: R -> N
  // 它告诉我们函数“f”是从实数集到自然数集的映射。
  // 抽象地说，这就是具体类型的准确定义。
  // 类型系统给我们提供了一些更强大的方式来表达这些集合。
  // 鉴于这些注释，编译器可以静态地 （在编译时）验证程序是合理的。
  // 也就是说，如果值（在运行时）不符合程序规定的约束，编译将失败。
  // 一般说来，类型检查只能保证不合理的程序不能编译通过。
  // 它不能保证每一个合理的程序都可以编译通过。
  // 随着类型系统表达能力的提高，我们可以生产更可靠的代码，
  // 因为它能够在我们运行程序之前验证程序的不变性（当然是发现类型本身的模型 bug！）。
  // 学术界一直很努力地提高类型系统的表现力，包括值依赖（value-dependent）类型！
  // **需要注意的是，所有的类型信息会在编译时被删去，因为它已不再需要。这就是所谓的擦除。**

  // Scala 中的类型
  // Scala 强大的类型系统拥有非常丰富的表现力。其主要特性有：
  // - 参数化多态性 粗略地说，就是泛型编程
  // - （局部）类型推断 粗略地说，就是为什么你不需要这样写代码 val i: Int = 12: Int
  // - 存在量化 粗略地说，为一些没有名称的类型进行定义
  // - 视窗 我们将下周学习这些；粗略地说，就是将一种类型的值“强制转换”为另一种类型

  // ###################### 参数化多态性 ######################
  // 多态性是在不影响静态类型丰富性的前提下，用来（给不同类型的值）编写通用代码的。
  // 例如，如果没有参数化多态性，
  // 一个通用的列表数据结构总是看起来像这样（事实上，它看起来很像使用泛型前的Java）：
  val l = 2 :: 1 :: "bar" :: "foo" :: Nil
  // 现在我们无法恢复其中成员的任何类型信息。
  //println(l)
  //println(l.head)

  // 所以我们的应用程序将会退化为一系列类型转换(“asInstanceOf[]”),
  // 并且会缺乏类型安全的保障（因为这些都是动态的）。
  // 多态性是通过指定 类型变量 实现的。

  // def drop1[A](l: List[A]) = l.tail

  // println(l)
  // println(drop1(l))

  // Scala 有秩 1 多态性
  // 粗略地说，这意味着在 Scala 中，
  // 有一些你想表达的类型概念“过于泛化”以至于编译器无法理解。

  // 假设你有一个函数
  def toList[A](a: A) = List(a)

  // 你希望继续泛型地使用它：
  // def foo[A, B](f: A => List[A], b: B) = f(b)
  // 这段代码不能编译，因为所有的类型变量只有在调用上下文中才被固定。即使你“钉住”了类型 B：
  // def foo[A](f: A => List[A], i: Int) = f(i)
  // …你也会得到一个类型不匹配的错误。

  // 类型推断
  // 静态类型的一个传统反对意见是，它有大量的语法开销。Scala 通过 类型推断 来缓解这个问题。
  // 在函数式编程语言中，类型推断的经典方法是 Hindley Milner 算法，它最早是实现在 ML 中的。
  // Scala 类型推断系统的实现稍有不同，但本质类似：推断约束，并试图统一类型。
  // 例如，在 Scala 中你无法这样做：
  // scala> { x => x }
  // <console>:7: error: missing parameter type
  //       { x => x }

  // 而在 OCaml 中你可以：
  // # fun x -> x;
  // - : 'a -> 'a = <fun>
  // 在 Scala 中所有类型推断是局部的 。Scala 一次分析一个表达式。例如：

  def id[T](x: T) = x

  // println(id(322))
  // println(id("hey"))
  // println(id(Array(1, 2, 3, 4)))

  // 类型信息都保存完好，Scala 编译器为我们进行了类型推断。请注意我们并不需要明确指定返回类型。

  // 变性 Variance
  // Scala 的类型系统必须同时解释类层次和多态性。
  // 类层次结构可以表达子类关系。
  // 在混合 OO 和多态性时，一个核心问题是：如果 T’ 是 T 一个子类，
  // Container[T’]应该被看做是 Container[T] 的子类吗？
  // 变性（Variance）注解允许你表达类层次结构和多态类型之间的关系：

  /**
   * <pre>
   * 名称	             || 含义                 || Scala 标记
   * 协变covariant      || C[T’]是 C[T] 的子类	 || [+T]
   * 逆变contravariant  || C[T] 是 C[T’]的子类	 || [-T]
   * 不变invariant      || C[T] 和 C[T’]无关	   || [T]
   * </pre>
   */
  // 子类型关系的真正含义：对一个给定的类型T，如果T’是其子类型，你能替换它吗？
  class Covariant[+A] // 协变
  // val cv: Covariant[AnyRef] = new Covariant[String]
  // val fail: Covariant[String] = new Covariant[AnyRef]
  // AnyRef 是String的子类型
  // String 不是AnyRef的子类型

  class Contravariant[-A] // 逆变
  // val cv: Contravariant[String] = new Contravariant[AnyRef]
  // val fail: Contravariant[AnyRef] = new Contravariant[String]
  // String 是 AnyRef 的子类型
  // AnyRef 不是 String 的子类型

  // 逆变似乎很奇怪。什么时候才会用到它呢？令人惊讶的是，函数特质的定义就使用了它！
  // trait Function1 [-T1, +R] extends AnyRef

  // 如果你仔细从替换的角度思考一下，会发现它是非常合理的。让我们先定义一个简单的类层次结构：

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
  // val getTweet: (Bird => String)

  // 标准动物库有一个函数满足了你的需求，但它的参数是 Animal。
  // 在大多数情况下，如果你说“我需要一个___，我有一个___的子类”是可以的。
  // 但是，在函数参数这里是逆变的。
  // 如果你需要一个接受参数类型 Bird 的函数变量，但却将这个变量指向了接受参数类型为 Chicken
  // 的函数，那么给它传入一个 Duck 时就会出错。
  // 然而，如果将该变量指向一个接受参数类型为 Animal 的函数就不会有这种问题：

  val getTweet: (Bird => String) = ((a: Animal) => a.sound)

  // 函数的返回值类型是协变的。
  // 如果你需要一个返回 Bird 的函数，但指向的函数返回类型是 Chicken，这当然是可以的。
  val hatch: (() => Bird) = (() => new Chicken)

  // ###################### 边界 ######################
  // Scala 允许你通过边界来限制多态变量。这些边界表达了子类型关系。
  // def cacophony[T](things: Seq[T]) = things map (_.sound)
  // <console>:7: error: value sound is not a member of type parameter T

  def biophony[T <: Animal](things: Seq[T]) = things map (_.sound)

  // println(biophony(Seq(new Chicken, new Bird)))

  // 类型下界也是支持的，这让逆变和巧妙协变的引入得心应手。
  // List[+T] 是协变的；一个 Bird 的列表也是 Animal 的列表。
  // List 定义一个操作::(elem T)返回一个加入了 elem 的新的 List。
  // 新的 List 和原来的列表具有相同的类型：

  val flock = List(new Bird, new Bird)
  // 鸟纲动物就是我们俗称的鸟类。
  // 鸡属于脊索动物门-脊椎动物亚门-鸟纲-突胸总目-鸡形目所以鸡是鸟类。
  // 鸡属于鸟纲，雉科家禽，据研究，鸡的听觉器官（内耳、外耳）对可听声有很好的敏感性。
  // 鸡栖息地面，除耳朵外，它的腿部还具有灵敏的振动感受器，震前可能感受到地声，出现行为异常。
  // println(new Chicken :: flock)

  // List 同样定义了::[B >: T](x: B) 来返回一个List[B]。
  // 请注意B >: T，这指明了类型B为类型T的超类。
  // 这个方法让我们能够做正确地处理在一个List[Bird]前面加一个 Animal 的操作：
  // 注意返回类型是 Animal。
  // println(new Animal :: flock)

  // ###################### 量化 ######################
  // 有时候，你并不关心是否能够命名一个类型变量，例如：
  def count[A](l: List[A]) = l.size

  // 这时你可以使用“通配符”取而代之：
  // def count(l: List[_]) = l.size

  // 这相当于是下面代码的简写：
  // def count(l: List[T forSome {type T}]) = l.size

  // 注意量化会的结果会变得非常难以理解：
  def drop1(l: List[_]) = l.tail

  // 突然，我们失去了类型信息！让我们细化代码看看发生了什么：
  // def drop1(l: List[T forSome {type T}]) = l.tail

  // 我们不能使用T因为类型不允许这样做。
  // 你也可以为通配符类型变量应用边界：
  def hashcodes(l: Seq[_ <: AnyRef]) = l map (_.hashCode)
  // hashcodes(Seq(1,2,3))
  // error: type mismatch;
  // found   : Int(1)
  // required: AnyRef
  // Note: primitive types are not implicitly converted to AnyRef.
  // You can safely force boxing by casting x.asInstanceOf[AnyRef].
  //       hashcodes(Seq(1,2,3))
  println(hashcodes(Seq("one", "two", "three")))

}