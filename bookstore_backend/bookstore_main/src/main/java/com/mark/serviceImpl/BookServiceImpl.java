package com.mark.serviceImpl;

import com.mark.dao.BookDao;
import com.mark.dao.LabelDao;
import com.mark.dao.LabelNodeDao;
import com.mark.dao.OrderDao;
import com.mark.entity.Book;
import com.mark.entity.LabelNode;
import com.mark.service.BookService;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookDao bookDao;

    @Autowired
    private LabelNodeDao labelNodeDao;

    @Autowired
    private LabelDao labelDao;

    @Override
    public Book findBookById(Integer id){
        return bookDao.findOne(id);
    }

    @Override
    public List<Book> getBooks() {
        return bookDao.getBooks();
    }

    @Override
    public Book modifyBook(Integer bookId, String name, String isbn,String type, String author, double price, String description, Integer inventory,String image){
        return bookDao.modifyBook(bookId,name,isbn,type,author,price,description,inventory,image);
    }

    @Override
    public List<Book> findByRelatedLabel(String labelName) {
        List<LabelNode> results=labelNodeDao.findRelated(labelName);
        if(results!=null){
            List<String> labelNameList=new ArrayList<>();
            for (LabelNode result:results){
                labelNameList.add(result.getLabelName());
            }
            return bookDao.findByBookIdList(labelDao.findBookIdByLabelNameList(labelNameList));
        }else{
            return null;
        }
    }

    @Override
    public List<Book> searchWithTextIndex(String search) throws IOException, ParseException {
        Directory directory= FSDirectory.open(new File("bookstore_main/src/main/resources/book_index"));
        IndexReader reader= DirectoryReader.open(directory);
        IndexSearcher indexSearcher=new IndexSearcher(reader);
        QueryParser queryParser=new QueryParser("description",new IKAnalyzer());
        Query query=queryParser.parse(search);
        TopDocs topDocs=indexSearcher.search(query,10);
        System.out.println("本次搜索共找到" + topDocs.totalHits + "条数据");
        ScoreDoc[] scoreDocs = topDocs.scoreDocs;
        List<Book> bookList=new ArrayList<>();
        for (ScoreDoc scoreDoc : scoreDocs) {
            Document doc = reader.document(scoreDoc.doc);
            System.out.println(doc.get("description"));
            bookList.add(bookDao.findOne(Integer.parseInt(doc.get("id"))));
        }
        return bookList;
    }

    @Override
    public void modifyBookState(Integer bookId,Integer state){
        bookDao.modifyBookState(bookId,state);
    }

    @Override
    public void addBook(String isbn,String name,String type,String author,Double price,String description,Integer inventory,String image){
        bookDao.addBook(isbn, name, type, author, price, description, inventory, image);
    }

}
