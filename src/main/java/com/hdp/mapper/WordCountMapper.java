package com.hdp.mapper;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileSplit;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class WordCountMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
    final Text text = new Text();
    final IntWritable one = new IntWritable(1);


    //how to remeber filename
    @Override
    protected void setup(Mapper<LongWritable, Text, Text, IntWritable>.Context context)
            throws IOException, InterruptedException {
        // TODO Auto-generated method stub
        // super.setup(context);
        FileSplit split = (FileSplit) context.getInputSplit();
        String filename = split.getPath().getName();
        System.out.println("current file name :"+filename);
    }

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] words = value.toString().split(" ");
        for (String word : words) {
            text.set(word);
            context.write(text, one);
        }
    }
}