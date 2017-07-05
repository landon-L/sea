package BaseLearning.InterviewProgramming;

import BaseLearning.Student;

import java.io.*;

/**
 * Created by Administrator on 2017/6/21.
 * 用来测试序列化和反序列化的使用
 * transient修饰的瞬态变量不会被序列化
 * static变量不会被序列化
 * 同时进行equals方法重写时候不会包含static变量的计算
 */
public class SerializeDemo {
    private static void serializeObject(Object object, String fileName) throws IOException {
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = new FileOutputStream(fileName);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(object);
        } catch (java.io.IOException e) {
            //此处可以记录日志
            System.out.println("create outputstream fail");
            e.printStackTrace();
        } finally {
            if (fos != null) {
                fos.close();
            }
            if (oos != null) {
                oos.close();
            }
        }
    }
    private static Object deSerializeObject(String fileName) throws ClassNotFoundException, IOException {
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            fis = new FileInputStream(fileName);
            ois = new ObjectInputStream(fis);
            return ois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            if (fis != null) {
                fis.close();
            }
            if (ois != null) {
                ois.close();
            }
        }
    }

    public static void main(String[] args) {
        //创建一个对象
        String fileName = "d:\\serializeDemo.txt";
        Student student = new Student("ldy", 18, "play pingpang");
        //序列化
        try {
            serializeObject(student, fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //反序列化
        Student studentCopy = null;
        try {
            studentCopy = (Student) deSerializeObject(fileName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //注意这里studentcopy可能为null
            if (student == studentCopy){
            System.out.println("I get the source student");
        } else if (student.equals(studentCopy)){
            System.out.println("I get the copy student");
        } else {
            System.out.println("I maybe bad");
        }
        System.out.println("student:" + student);
        System.out.println("studentCopy:" + studentCopy);
    }
}
