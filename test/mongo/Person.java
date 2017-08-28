package mongo;

/**
 * Created by Lodour on 2017/8/28 08:39.
 */
class Person {

    private String id;
    private String name;
    private int age;

    Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    String getId() {
        return id;
    }

    String getName() {
        return name;
    }

    int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Person [id=" + id + ", name=" + name + ", age=" + age + "]";
    }

}