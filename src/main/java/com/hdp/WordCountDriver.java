package com.hdp;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;
import com.hdp.mapper.WordCountMapper;
import com.hdp.reducer.WordCountReduce;

public class WordCountDriver extends Configured implements Tool {
    public static void main(String[] args) throws Exception {
            Configuration con = new Configuration();
            // con.set("mapreduce.output.fileoutputformat.compress","true");
            // con.set("mapreduce.output.fileoutputformat.compress.type","RECORD");
            // con.set("mapreduce.output.fileoutputformat.compress.codec","org.apache.hadoop.io.compress.SnappyCodec");
            
            // con.set("mapreduce.framework.name","yarn"); //使用 yarn 模式

            int status = ToolRunner.run(con,new WordCountDriver(),args);

            System.exit(status);

    }

    @Override
    public int run(String[] args) throws Exception{


        Job job = Job.getInstance(getConf(), WordCountDriver.class.getSimpleName());
        job.setJarByClass(WordCountDriver.class);

        //自定义mapper和reduce
        job.setMapperClass(WordCountMapper.class);
        job.setReducerClass(WordCountReduce.class);
        
        //mapper 输出 key value
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);

        //reduce 输出 key value
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);


        // job.setCombinerClass(WordCombiner.class);
        FileInputFormat.setInputPaths(job,new Path(args[0]));
        FileOutputFormat.setOutputPath(job,new Path(args[1]));

        return job.waitForCompletion(true)?0:1;
    }

    
}