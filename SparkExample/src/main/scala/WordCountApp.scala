import org.apache.spark.{SparkConf, SparkContext}

object WordCountApp {
  def main(args: Array[String]): Unit = {
    //val conf = new SparkConf().setAppName("WordCountApp").setMaster("local[*]")
    val conf = new SparkConf().setAppName("WordCountApp").setMaster("spark://hadoop-amy:7077")
    val sc = SparkContext.getOrCreate(
      conf
    );
    //val input = sc.textFile("resource/tale")
    val input = sc.textFile("hdfs://hadoop-amy:9000/data/tale")
    val wordCount = input.flatMap(
      line => line.split("\\s").filter(w => w.trim().length() > 0)
    ).map(w => (w,1)).reduceByKey((a,b)=>a+b)
    wordCount.collect().foreach(wordNum => println(wordNum._1 + " : " + wordNum._2))
    sc.stop()
  }
}
