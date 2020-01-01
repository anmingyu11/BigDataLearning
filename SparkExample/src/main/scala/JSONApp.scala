import org.apache.spark.{SparkConf, SparkContext}

import scala.util.parsing.json.JSON

object JSONApp {

  def main(args: Array[String]): Unit = {
    val inputFile = "resource/people.json"
    val conf = new SparkConf().setAppName("jsonApp").setMaster("local[*]")
    val sc = new SparkContext(conf)
    val jsonStr = sc.textFile(inputFile)
    val result = jsonStr.map(s => JSON.parseFull(s))
    result.foreach({
      r =>
        r match {
          case Some(map: Map[String, Any]) => println(map)
          case None => println("Parsing failed")
          case other => println("Unknown data structure:" + other)
        }
    })
  }

}
