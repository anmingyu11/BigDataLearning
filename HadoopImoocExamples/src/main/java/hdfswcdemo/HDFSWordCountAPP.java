package hdfswcdemo;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;

import main.Constants;

public class HDFSWordCountAPP {

    private static FileSystem getFileSystem() throws IOException, InterruptedException {
        Configuration conf = new Configuration();
        conf.set("dfs.client.use.datanode.hostname", "true");
        return FileSystem.get(
                URI.create(Constants.HDFS_URI)
                , conf
                , "hadoop"
        );
    }

    private static void println(Object o) throws IOException {
        String localOutFile = "log";
        println(o, localOutFile);
    }

    private static void println(Object o, String file) throws IOException {
        File fp = new File(file);
        System.out.println(o);
        FileWriter fw = new FileWriter(new File(file), true);
        fw.write(o.toString() + "\n");
        fw.flush();
        fw.close();
    }

    public static void main(String[] args) throws Exception {
        String logFile = "log";
        String divideLine = "------";

        // 删除log
        File file = new File(logFile);
        if (file.exists()) {
            file.delete();
        } else {
            if (!file.createNewFile()) {
                throw new Exception();
            }
        }
        println("log write to " + file.getAbsoluteFile());

        FileSystem fs = getFileSystem();

        // 删除hdfs上的输入输出文件
        Path input = new Path(Constants.INPUT_PATH);
        Path output = new Path(Constants.OUTPUT_PATH + Constants.OUTPUT_FILE);
        Path outputPath = new Path(Constants.OUTPUT_PATH);
        println(divideLine + " start deleting " + divideLine);
        boolean inputDel = fs.delete(input, true);
        boolean outputDel = fs.delete(output, true);
        println(" input : " + Constants.INPUT_PATH + " : " + inputDel
                + "\n"
                + " output : " + Constants.OUTPUT_PATH + " : " + outputDel
        );

        // 创建output path
        boolean outputPathExist = fs.exists(outputPath);
        if (!outputPathExist) {
            println("Output path not exist : mkd it ");
            boolean mk = fs.mkdirs(outputPath);
            if (!mk) {
                throw new Exception();
            }
            println("mkdirs? " + mk);
        } else {
            println("Output path exist");
        }

        // 上传本地的tale文件
        println(divideLine + " start uploading tale " + divideLine);
        fs.copyFromLocalFile(new Path("src/main/resources/tale"), input);
        FileStatus[] inputFileStatus = fs.listStatus(input);
        if (inputFileStatus == null || inputFileStatus.length < 1) {
            throw new Exception();
        }
        for (FileStatus fileStatus : inputFileStatus) {
            println("file status : " + fileStatus);
        }
        // mapper
        println(divideLine + " mapping the tale " + divideLine);
        FSDataInputStream fsdis = fs.open(input);
        BufferedReader reader = new BufferedReader(new InputStreamReader(fsdis));
        MapperContext mc = new MapperContext();
        CaseIgnoreMapper mapper = new CaseIgnoreMapper();
        String line = "";
        while ((line = reader.readLine()) != null) {
            // 业务部分
            mapper.map(mc, line);
        }
        reader.close();
        fsdis.close();
        println(divideLine + " map size : " + mc.getMap().size() + divideLine);

        // 缓存结果到wc 上
        println(divideLine + " uploading result and log to hdfs" + divideLine);
        FSDataOutputStream out = fs.create(output);
        for (String k : mc.getMap().keySet()) {
            Integer v = mc.getMap().get(k);
            out.write((k + " : " + v + " \n").getBytes());
        }
        out.hflush();
        out.close();

        fs.copyFromLocalFile(new Path(logFile), new Path(Constants.OUTPUT_PATH + "log"));
        fs.close();

    }

}
