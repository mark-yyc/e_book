package com.mark;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.apache.log4j.BasicConfigurator;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.*;
import java.util.*;

public class HBaseEndPoint {
    public static final String HDFS_PATH = "hdfs://hadoop:9000";
    public static final String fileNamePrefix="hbase/src/main/resources/hdfs/";
    public static final String CF="KEYWORD";
    public static final String TABLE_NAME="book";
    public static FileSystem fileSystem = null;
    public static Configuration configuration = null;

    public static Map<String,Integer> statistic=new HashMap<>();

    public static void initStatistic(){
        statistic.clear();
        String path = "hadoop/src/main/resources/keyword.txt";
        File file = new File(path);
        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));//构造一个BufferedReader类来读取文件

            String s;
            while((s = br.readLine())!=null){
                statistic.put(s,0);
            }
            br.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void cat(String fileName) throws Exception {
        FSDataInputStream in = fileSystem.open(new Path(fileName));
        BufferedReader d = new BufferedReader(new InputStreamReader(in));
        String line;
        String [] tmp=fileName.split("/");
        int i=1;
        resetStatistic();
        while ((line = d.readLine()) != null) {
            System.out.println(line);
            String [] analysedWordList=analyse(line);
            for(String word:analysedWordList){
                if(statistic.containsKey(word)){
                    statistic.put(word,statistic.get(word)+1);
                }
            }
            String rowKey=tmp[tmp.length-1]+"-"+i;
            HBaseUtil.addRow(TABLE_NAME,rowKey,CF,statistic);
            i++;
        }
        d.close();
        in.close();
    }

    public static void resetStatistic(){
        for(Map.Entry<String, Integer> entry : statistic.entrySet()) {
            statistic.put(entry.getKey(), 0);
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

    public static void main(String[] args) throws Exception {
//        BasicConfigurator.configure();
        configuration = new Configuration();
        configuration.set("dfs.defaultFS", HDFS_PATH);
        fileSystem = FileSystem.get(configuration);
        String entireName=fileNamePrefix+"input";
        FileStatus[] fileStatuses = fileSystem.listStatus(new Path(entireName));
        initStatistic();
        HBaseUtil.deleteTable(TABLE_NAME);
        HBaseUtil.createTable(TABLE_NAME,CF);
        for (FileStatus fileStatus : fileStatuses) {
            String path = fileStatus.getPath().toString();
            cat(path);
        }
        HBaseUtil.scanAll(TABLE_NAME);
    }
}
