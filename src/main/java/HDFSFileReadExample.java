import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 读取文件
 * Created by liunn on 2017/12/25.
 */
public class HDFSFileReadExample {
    public static void main(String[] args) {
        //设置HADOOP_USER_NAME这个环境变量的值为hadoop环境中的用户，不设置的话会出现
        //org.apache.hadoop.security.AccessControlException异常
        System.setProperty("HADOOP_USER_NAME","hadoop");
        try {
            Configuration conf = new Configuration();
            conf.set("fs.defaultFS", "hdfs://172.17.243.181:9000");
            conf.set("fs.hdfs.impl", "org.apache.hadoop.hdfs.DistributedFileSystem");

            FileSystem fs = FileSystem.get(conf);
            Path file = new Path("test001");
            FSDataInputStream getIt = fs.open(file);
            BufferedReader d = new BufferedReader(new InputStreamReader(getIt));
            String content = d.readLine();//读取文件一行
            System.out.println(content);
            d.close();//关闭文件
            fs.close();//关闭hdfs

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
