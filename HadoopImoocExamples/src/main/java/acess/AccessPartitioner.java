package acess;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

public class AccessPartitioner extends Partitioner<Text, Access> {

    @Override
    public int getPartition(Text text, Access access, int numPartitions) {
        String str = text.toString();
        if (str.startsWith("13")) {
            return 0;
        } else if (str.startsWith("15")) {
            return 1;
        } else {
            return 2;
        }
    }

}