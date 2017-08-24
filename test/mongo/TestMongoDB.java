package mongo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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
    @Autowired
    private MongoTemplate mongoTemplate;

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