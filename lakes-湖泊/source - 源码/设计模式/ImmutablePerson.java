package createanddestroyobject;

import java.net.SocketPermission;

/**
 * Created by Administrator on 2017/6/21.
 * 构造器模式
 * 遇到多个构造器参数时的考虑采用构建器
 * 1.采用重叠构造器模式，适用于参数不是太多，5个左右
 * 2.采用构建器方式，使用参数很多的情况，且参数后期可扩展性高。如下所示：
 *
 * 采用构建器的好处：
 * 1.对每个参数可以加强约束，当约束条件没有得到时抛出异常。
 * 2.可以有多个可变参数，每个setting方法都可以包含一个可变参数，更加灵活，而构造器只能有一个可变参数。
 * 不足之处：参数冗长，需要重复一遍所有的参数
 */
public class ImmutablePerson {
    private final String id;
    private final String name;
    private final Integer age;
    private final String hobby;
    private final EnumUtils.SEX sex;

    public static class Builder {
        private final String id;
        private final String name;

        //next attr is not required
        private Integer age = 0;
        private String hobby = null;
        private EnumUtils.SEX sex = EnumUtils.SEX.NON;

        public Builder(String id, String name) {
            this.id = id;
            this.name = name;
        }

        //如下为setting方法，可以进行参数信息的校验，不满足条件，抛出异常即可
        public Builder age(Integer val) {
            this.age = val;
            return this;
        }
        public Builder hobby(String val) {
            this.hobby = val;
            return this;
        }
        public Builder sex(EnumUtils.SEX val) {
            this.sex = val;
            return this;
        }

        //这一招是怎么玩的， 666
        public ImmutablePerson build() {
            return new ImmutablePerson(this);
        }
    }
    /**
     * 此处注意可以直接调用内部类的私有方法        
     * 可以在person域对参数进行集体验证，不满足条件抛出参数异常。
     */
    private ImmutablePerson(Builder builder) {
        this.id = builder.id;
        this.name =builder.name;
        this.age = builder.age;
        this.sex = builder.sex;
        this.hobby = builder.hobby;
    }

    // TODO: 2017/6/21 如下可以加入完成初始化的其他函数

    @Override
    public String toString() {
        return "ImmutablePerson{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", hobby='" + hobby + '\'' +
                ", sex=" + sex +
                '}';
    }

    public static void main(String[] args) {
        ImmutablePerson deadMan = new Builder("1", "lidongyang").age(18).sex(EnumUtils.SEX.MAN)
                .hobby("play tennis and pingpang").build();
        System.out.println(deadMan.toString());
    }
}
