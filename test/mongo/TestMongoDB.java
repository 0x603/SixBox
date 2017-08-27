package mongo;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;
import static org.springframework.data.mongodb.core.query.Update.update;

/**
 * Created by Lodour on 2017/8/24 22:26.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-config.xml")
public class TestMongoDB {
    /**
     * 测试文件名
     */
    private final String testFileName = "test.txt";

    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private GridFsOperations gridFsOperations;

    @Test
    public void testMongoDB() {
        Person p = new Person("Joe", 11);

        // Create
        mongoTemplate.insert(p);
        System.out.println("Insert: " + p);

        // Retrieve
        p = mongoTemplate.findById(p.getId(), Person.class);
        System.out.println("Found: " + p);

        // Update
        mongoTemplate.updateFirst(query(where("name").is("Joe")), update("age", 22), Person.class);
        p = mongoTemplate.findOne(query(where("name").is("Joe")), Person.class);
        System.out.println("Updated: " + p);

        // Delete
        mongoTemplate.remove(p);
        System.out.println("Delete: " + p);

        // Check that deletion worked
        List<Person> people = mongoTemplate.findAll(Person.class);
        System.out.println("Number of people: " + people.size());

        // Clear
        mongoTemplate.dropCollection(Person.class);
    }

    @Test
    public void testGridFs() throws IOException {
        // TODO: 创建测试文件

        // 文件元信息
        DBObject metaData = new BasicDBObject();
        metaData.put("extra1", "anything 1");
        metaData.put("extra2", "anything 2");

        // 获得文件流并保存
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(testFileName);
            gridFsOperations.store(inputStream, testFileName, metaData);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        // TODO: 查询保存的文件

        // TODO: 删除保存的文件

        // TODO: 查询删除的文件

        // TODO: 删除测试文件
    }
}


class Person {

    private String id;
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Person [id=" + id + ", name=" + name + ", age=" + age + "]";
    }

}