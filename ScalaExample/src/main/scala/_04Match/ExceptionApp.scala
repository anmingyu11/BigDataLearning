package _04Match

object ExceptionApp extends App {

  try {
    val i = 10 / 0
    println(i)
  } catch {
    case e => println(e)
  } finally {
  }

}
