import org.apache.spark.{SparkConf, SparkContext}

object Combine {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("Combine").setMaster("spark://hadoop-amy:7077")
    val sc = new SparkContext(conf)
    val data = sc.parallelize(Array(
      ("company1", 92),
      ("company1", 85),
      ("company1", 82),
      ("company2", 78),
      ("company2", 96),
      ("company2", 85),
      ("company3", 85),
      ("company3", 94),
      ("company3", 80)
    ), 3)
    val res = data.combineByKey(
      (income) => (income, 1),
      (acc: (Int, Int), income) => (acc._1 + income, acc._2 + 1),
      (acc1: (Int, Int), acc2: (Int, Int)) => (acc1._1 + acc2._1, acc1._2 + acc2._2)
    ).map {
      case (key, value) => (key, value._1, value._1 / value._2.toFloat)
    }
    res.repartition(1).saveAsTextFile("./result")
  }
}
