package mrwcdemo;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

import main.Constants;

public class WordCountApp {

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {

        System.setProperty("HADOOP_USER_NAME", "hadoop");
        Configuration conf = new Configuration();
        conf.set("dfs.client.use.datanode.hostname", "true");
        conf.set("fs.defaultFS", Constants.HDFS_URI);

        Job job = Job.getInstance(conf);

        job.setJarByClass(WordCountApp.class);

        job.setMapperClass(WordCountMapper.class);
        job.setReducerClass(WordCountReducer.class);
        //job.setCombinerClass(WordCountReducer.class);

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        FileInputFormat.setInputPaths(job, new Path("/data/tale"));
        FileOutputFormat.setOutputPath(job, new Path("/mapred_test/wc.out_withoutCombiner"));

        boolean result = job.waitForCompletion(true);
        System.exit(result ? 0 : -1);
    }

}
