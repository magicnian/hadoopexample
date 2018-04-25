package cn.magicnian.example;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.mapreduce.lib.partition.HashPartitioner;

import java.net.URI;

/**
 * Created by liunn on 2017/12/27.
 */
public class WordCountExample {

    public static final String INPUT_PATH = "hdfs://hadoop-master:9000/testdir/input/word.txt";

    public static final String OUTPUT_PATH = "hdfs://hadoop-master:9000/testdir/output/wordcount";

    public static void main(String[] args) throws Exception{
        Configuration conf = new Configuration();

        //删除输出路径的已有生成文件
        FileSystem fs = FileSystem.get(new URI(INPUT_PATH),conf);
        Path outPath = new Path(OUTPUT_PATH);
        if(fs.exists(outPath)){
            fs.delete(outPath,true);
        }

//        Job job = new Job(conf,"WordCount");
        Job job = Job.getInstance(conf,"WordCount");
        job.setJarByClass(WordCountExample.class);

        //指定输入目录
        FileInputFormat.setInputPaths(job,new Path(INPUT_PATH));
        //指定对输入数据进行格式化处理的类（可以省略）
        job.setInputFormatClass(TextInputFormat.class);
        //指定自定义的Mapper类
        job.setMapperClass(MyMapper.class);
        //指定map输出的<K,V>类型（如果<k3,v3>的类型与<k2,v2>的类型移至则可以省略）
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(LongWritable.class);
        //分区（可以省略）
        job.setPartitionerClass(HashPartitioner.class);
        //设置要运行的Reducer的数量（可以省略）
        job.setNumReduceTasks(1);
        //指定自定义的Reducer类
        job.setReducerClass(MyReducer.class);
        //指定reduce输出的<K,V>类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(LongWritable.class);
        //指定输出目录
        FileOutputFormat.setOutputPath(job,new Path(OUTPUT_PATH));
        //指定对输出数据进行格式化处理的类（可以省略）
        job.setOutputFormatClass(TextOutputFormat.class);
        //提交作业
        boolean success = job.waitForCompletion(true);
        if(success){
            System.out.println("Success");
            System.exit(0);
        }else{
            System.out.println("Failed");
            System.exit(1);
        }

    }
}
