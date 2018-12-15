package BaseLearning;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/6/21.
 */
public class Student implements Serializable {
    //这里暂时没有添加序列化id
    private String name;
    private transient Integer age;
    private static String hobby;

    public Student(String name, Integer age, String hobby) {
        this.name = name;
        this.age = age;
        this.hobby = hobby;
    }

    static {
        System.out.println("I'm a student");
    }
    public void cal() {
        System.out.println("I'm a function");
    }
    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", hobby='" + hobby + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Student student = (Student) o;

        if (!name.equals(student.name)) return false;
        return age.equals(student.age);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + age.hashCode();
        return result;
    }
}
