import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

/**
 * Created by liunn on 2017/12/25.
 */
public class HDFSFileIsExist {

    public static void main(String[] args) {
        try {
            String fileName = "test001";
            Configuration conf = new Configuration();
            conf.set("fs.defaultFS","hdfs://localhost:9000");
            conf.set("fs.hdfs.impl","org.apache.hadoop.hdfs.DistributedFileSystem");

            FileSystem fs = FileSystem.get(conf);
            if(fs.exists(new Path(fileName))){
                System.out.printf("文件存在");
            }else{
                System.out.printf("文件不存在");
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
