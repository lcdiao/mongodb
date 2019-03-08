import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by diao on 2019/3/8
 */
public class MongoDBTest {
    public static void main(String[] args) {
        MongoDBTest test = new MongoDBTest();

        //test.connect();

        //test.save();
        //test.update();

        test.delete();
        test.query();

    }


    public MongoCollection<Document> getCollection(){
        //连接到mongodo服务
        MongoClient mongoClient = new MongoClient("localhost",27017);

        //连接到数据库
        MongoDatabase mongoDatabase = mongoClient.getDatabase("dbtest");
        System.out.println("连接数据库成功！！！");

        //创建集合      若已经存在会报错
        //mongoDatabase.createCollection("colName");

        //获取集合
        MongoCollection<Document> collection = mongoDatabase.getCollection("colName");
        return collection;
    }

    public void connect(){
        //连接到mongodo服务
        MongoClient mongoClient = new MongoClient("localhost",27017);

        //连接到数据库
        MongoDatabase mongoDatabase = mongoClient.getDatabase("dbtest");
        System.out.println("连接数据库成功！！！");

        //创建集合
        mongoDatabase.createCollection("colName");

        //获取集合
        MongoCollection<Document> collection = mongoDatabase.getCollection("colName");

        System.out.println("查看集合:" + collection.toString());
    }



    public void save(){

        //连接到mongodo服务
        MongoClient mongoClient = new MongoClient("localhost",27017);

        //连接到数据库
        MongoDatabase mongoDatabase = mongoClient.getDatabase("dbtest");
        System.out.println("连接数据库成功！！！");

        //创建集合      若已经存在会报错
        //mongoDatabase.createCollection("colName");

        //获取集合
        MongoCollection<Document> collection = mongoDatabase.getCollection("colName");

        Document document = new Document("username","MongoDB")
                .append("description","a user")
                .append("age",10)
                .append("sex","boy");
        Document document1 = new Document("id",11).append("username","hello").append("age",20);
        List<Document> documents = new ArrayList<Document>();
        documents.add(document);
        documents.add(document1);
        collection.insertMany(documents);

    }


    public void query(){

        MongoCollection<Document> collection = getCollection();

        //获取迭代器
        FindIterable<Document> findIterable = collection.find();
        //获取迭代游标
        MongoCursor<Document> mongoCursor = findIterable.iterator();
        //执行迭代检索文档
        while(mongoCursor.hasNext()){
            System.out.println(mongoCursor.next());
        }
    }

    public void update(){
        MongoCollection<Document> collection = getCollection();
        //年龄等于10的设置likes=20
        collection.updateMany(Filters.eq("age",10),new Document("$set",new Document("likes",30)));
    }

    public void delete(){
        MongoCollection<Document> collection = getCollection();

        //删除符合条件的第一条文档
        collection.deleteOne(Filters.eq("likes",30));
        //删除所有符合条件的文档
        //collection.deleteMany();
    }

}
