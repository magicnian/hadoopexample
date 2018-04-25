import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

/**
 * 写入文件
 * Created by liunn on 2017/12/25.
 */
public class HDFSFileWriteExample {

    public static void main(String[] args) {
        try {
            Configuration conf = new Configuration();
            conf.set("fs.defaultFS", "hdfs://localhost:9000");
            conf.set("fs.hdfs.impl", "org.apache.hadoop.hdfs.DistributedFileSystem");

            FileSystem fs = FileSystem.get(conf);
            byte[] buff = "Hello Hadoop!".getBytes();//要写入的内容
            String fileName = "test001";//需要写入的文件名
            FSDataOutputStream os = fs.create(new Path(fileName));
            os.write(buff, 0, buff.length);

            System.out.println("Create:" + fileName);
            os.close();
            fs.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
