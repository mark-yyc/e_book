package com.mark.util;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mark.BookstoreApplication;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.*;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;

public class LuceneIndexWriter {
    private static String readJsonFile(String fileName){
        String jsonStr="";
        try{
            File jsonFile=new File(fileName);
            FileReader fileReader=new FileReader(jsonFile);
            Reader reader=new InputStreamReader(new FileInputStream(jsonFile), StandardCharsets.UTF_8);
            int ch=0;
            StringBuffer stringBuffer=new StringBuffer();
            while((ch=reader.read())!=-1){
                stringBuffer.append((char)ch);
            }
            fileReader.close();
            reader.close();
            jsonStr=stringBuffer.toString();
            return jsonStr;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static void writeIndex() throws IOException {
        String jsonStr=readJsonFile("bookstore_main/src/main/resources/book_description.json");
        JsonArray jsonArray= BookstoreApplication.gson.fromJson(jsonStr,JsonArray.class);
        Collection<Document> docs=new ArrayList<>();
        for(JsonElement jsonElement:jsonArray){
            JsonObject jsonObject=jsonElement.getAsJsonObject();
            String description=jsonObject.get("description").getAsString();
            int id=jsonObject.get("id").getAsInt();
            Document document=new Document();
            document.add(new TextField("description",description, Field.Store.YES));
            document.add(new IntField("id",id,Field.Store.YES));
            docs.add(document);
        }
        Directory directory= FSDirectory.open(new File("bookstore_main/src/main/resources/book_index"));
        Analyzer analyzer=new IKAnalyzer();
        IndexWriterConfig conf=new IndexWriterConfig(Version.LATEST,analyzer);
        conf.setOpenMode(IndexWriterConfig.OpenMode.CREATE);
        IndexWriter indexWriter=new IndexWriter(directory,conf);
        indexWriter.addDocuments(docs);
        indexWriter.commit();
        indexWriter.close();
    }
    public static void main(String[] args) throws IOException {
        writeIndex();
    }
}