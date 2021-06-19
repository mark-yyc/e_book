package com.mark;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.log4j.BasicConfigurator;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.*;
import java.util.*;

public class KeyWordCount {

    public static AtomicAmount mapperNumber=new AtomicAmount();
    public static AtomicAmount reducerNumber=new AtomicAmount();

    public static class TokenizerMapper
            extends Mapper<Object, Text, Text, IntWritable> {

        private final static IntWritable one = new IntWritable(1);
        private Text word = new Text();

        @Override
        public void setup(Context context) {
            mapperNumber.increment();
        }


        @Override
        public void map(Object key, Text value, Context context
        ) throws IOException, InterruptedException {
            String line = value.toString();
            String[] result=analyse(line);
            for(String keyWord:result){
                word.set(keyWord);
                context.write(word, one);
            }
        }
    }

    private static String[] analyse(String search) throws IOException {
        Objects.requireNonNull(search);
        //prepare
        Analyzer analyzer = new IKAnalyzer(true);
        StringReader reader = new StringReader(search);
        TokenStream tokenStream = analyzer.tokenStream("", reader);
        tokenStream.reset();
        CharTermAttribute term = tokenStream.getAttribute(CharTermAttribute.class);
        Set<String> wordSet = new HashSet<>();
        //get word from analyzer
        while (tokenStream.incrementToken())
            wordSet.add( term.toString());
        //get word from single word
        for (int i = 0; i < search.length(); ++i)
            wordSet.add(String.valueOf(search.charAt(i)));
        //wordSet to wordArray
        String[] wordArray = new String[wordSet.size()];
        int i = 0;
        for (String s : wordSet)
            wordArray[i++] = s;
        //search
        reader.close();
        return wordArray;
    }

    public static class IntSumReducer
            extends Reducer<Text,IntWritable,Text,IntWritable> {
        private IntWritable result = new IntWritable();

        final List<String> keyWordList=new ArrayList<>();

        @Override
        public void setup(Reducer.Context context) {
            reducerNumber.increment();
        }

        private void initKeyWordList(){
            keyWordList.clear();
            String path = "hadoop/src/main/resources/keyword.txt";
            File file = new File(path);
            try{
                BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));//构造一个BufferedReader类来读取文件

                String s;
                while((s = br.readLine())!=null){
                    keyWordList.add(s);
                }
                br.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }

        public void reduce(Text key, Iterable<IntWritable> values,
                           Context context
        ) throws IOException, InterruptedException {
            initKeyWordList();
            int sum = 0;
            for (IntWritable val : values) {
                sum += val.get();
            }
            if(keyWordList.contains(key.toString())){
                System.out.println("find key word: "+key.toString());
                result.set(sum);
                context.write(key, result);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BasicConfigurator.configure();
        Configuration conf = new Configuration();
        conf.set("dfs.defaultFS", "hdfs://localhost:9000");
        Job job = Job.getInstance(conf, "key word count");
        job.setJarByClass(KeyWordCount.class);
        job.setMapperClass(TokenizerMapper.class);
        job.setCombinerClass(IntSumReducer.class);
        job.setReducerClass(IntSumReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        FileInputFormat.addInputPath(job, new Path("hadoop/src/main/resources/hdfs/input"));
        FileOutputFormat.setOutputPath(job, new Path("hadoop/src/main/resources/hdfs/output"));
        if(job.waitForCompletion(true)){
            System.out.println("number of mapper: "+mapperNumber.getValue());
            System.out.println("number of reducer: "+reducerNumber.getValue());
            System.exit(0);
        }else{
            System.exit(1);
        }
    }
}
