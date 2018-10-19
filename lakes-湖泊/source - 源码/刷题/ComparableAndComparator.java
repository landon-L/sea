package BaseLearning.InterviewProgramming;

import createanddestroyobject.EnumUtils;
import org.junit.Test;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;
import java.util.TreeMap;

import static javafx.scene.input.KeyCode.T;

/**
 * Created by Administrator on 2017/6/26.
 * 实现排序有两种方法，
 * 一：实现comparable接口，重写compareTo方法，这样类本身会有排序功能,
 * 在treeSet和treeMap集合中会有按照重写的compareTo方法来进行排序
 * 二：自己定义比较器，实现comparator接口，重写compare方法，在对集合元素进行排序时会用到比较器。
 */
public class ComparableAndComparator {
    @Test
    public void testIntegerSort() {
        String p1 = "yujiaojiao";
        String p2 = "lidongyang";
        int res = p1.compareTo(p2);

        Random rd = new Random();
        Integer[] integers = new Integer[20];
        for(int i = 0; i < 20; i++) {
            integers[i] = rd.nextInt(1000);
        }
        Arrays.sort(integers);
        System.out.println("Arrays内部默认排序（升序）" + Arrays.asList(integers));
        Arrays.sort(integers, new MyComparator<Integer>());
        System.out.println("使用自定义排序器（降序）" + Arrays.asList(integers));
    }

    @Test
    public void testObjectSort() {
        TreeMap<Person,String> myTreeMap = new TreeMap();
        for (int i = 0; i < 20; i++) {
             myTreeMap.put(new Person("ldy", i + 10, EnumUtils.SEX.MAN),"test" + i);
        }
        System.out.println("treemap排序,默认使用对象的compareTo方法进行排序" + myTreeMap.toString());
    }
}

/**
 * 定义自己的比较器实现comparator接口，可以实现作为集合类型Collection的比较方法实现
 * @param <T>
 */
class MyComparator<T> implements Comparator<T> {

    @Override
    public int compare(T o1, T o2) {
        //判断一个类型是否为整形
        if (T.getClass().equals(Integer.class)) {
            int v1 = ((Integer)o1).intValue();
            int v2 = ((Integer)o2).intValue();
            return v1 > v2 ? 1 : (v1 == v2 ? 0 : -1);
        } else if (T.getClass().equals(String.class)) {
            return ((String)o1).compareTo((String)o2);
        }
        //这里如何处理才会比较好呢，记录日志抛出异常
        return -1;
    }
}

class Person implements Comparable<Person>{
    private String name;
    private Integer age;
    private EnumUtils.SEX sex;

    public Person(String name, Integer age, EnumUtils.SEX sex) {
        this.name = name;
        this.age = age;
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", sex=" + sex +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public EnumUtils.SEX getSex() {
        return sex;
    }

    public void setSex(EnumUtils.SEX sex) {
        this.sex = sex;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        if (name != null ? !name.equals(person.name) : person.name != null) return false;
        if (age != null ? !age.equals(person.age) : person.age != null) return false;
        return sex == person.sex;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (age != null ? age.hashCode() : 0);
        result = 31 * result + (sex != null ? sex.hashCode() : 0);
        return result;
    }

    @Override
    public int compareTo(Person o) {
        int res = name.compareTo(o.getName());
        if (res != 0) {
            return res;
        }
        res = age -o.getAge();
        if (res != 0) {
            return (-1) * res;
        }
        return 0;
    }
}
