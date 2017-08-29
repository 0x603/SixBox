package mongo;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.gridfs.GridFSDBFile;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static mongo.FileUtils.testFileName;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;
import static org.springframework.data.mongodb.core.query.Update.update;
import static org.springframework.data.mongodb.gridfs.GridFsCriteria.whereFilename;

/**
 * Created by Lodour on 2017/8/24 22:26.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-config.xml")
public class TestMongoDB {

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
        // 创建测试文件
        FileUtils.createTestFile();

        // 文件元信息
        DBObject metaData = new BasicDBObject();
        metaData.put("extra1", "anything 1");
        metaData.put("extra2", "anything 2");

        // 获得文件流并保存
        InputStream inputStream = new FileInputStream(testFileName);
        gridFsOperations.store(inputStream, testFileName, metaData);
        inputStream.close();

        // 查询保存的文件
        List<GridFSDBFile> result = gridFsOperations.find(query(whereFilename().is(testFileName)));
        Assert.assertEquals(result.size(), 1);

        // 删除保存的文件
        gridFsOperations.delete(query(whereFilename().is(testFileName)));

        // 查询删除的文件
        result = gridFsOperations.find(query(whereFilename().is(testFileName)));
        Assert.assertEquals(result.size(), 0);

        // 删除测试文件
        FileUtils.removeTestFile();
    }
}
