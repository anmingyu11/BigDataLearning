import org.apache.spark.{Partitioner, SparkConf, SparkContext}

class UsridPartitioner(numParts:Int) extends Partitioner {

  override def numPartitions: Int = numParts

  override def getPartition(key: Any): Int = {
    key.toString.toInt%10
  }

}

object UsridTest {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local[*]").setAppName("simplePartition")
    val sc = new SparkContext(conf)
    val data = sc.parallelize(1 to 100,5)
    data.map((_,1)).partitionBy(new UsridPartitioner(10)).map(_._1).saveAsTextFile("output/usridPartitioner/")
    sc.stop()
  }

}
