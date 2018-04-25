package cn.magicnian.example;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * Created by liunn on 2017/12/27.
 */
public class MyMapper extends Mapper<LongWritable, Text, Text, LongWritable> {

    @Override
    protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, LongWritable>.Context context) throws IOException, InterruptedException {
        String[] arrays = value.toString().split(" ");
        for (String word : arrays) {
            context.write(new Text(word),new LongWritable(1L));
        }
    }
}
