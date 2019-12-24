package main;

import java.util.Properties;

public class Constants {
    public static final String INPUT_PATH;
    public static final String OUTPUT_PATH;
    public static final String OUTPUT_FILE;
    public static final String HDFS_URI;
    // public static final String MAPPER_CLASS;

    private static Properties properties = new Properties();

    static {
        try {
            properties.load(
                    Constants.class
                            .getClassLoader()
                            .getResourceAsStream("Conf.properties")
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
        INPUT_PATH = properties.getProperty("INPUT_PATH");
        OUTPUT_PATH = properties.getProperty("OUTPUT_PATH");
        OUTPUT_FILE = properties.getProperty("OUTPUT_FILE");
        HDFS_URI = properties.getProperty("HDFS_URI");
    }

}
