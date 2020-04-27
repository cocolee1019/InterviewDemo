package chapter4;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Demo4 {

    public static void main(String[] args) {
        List aList = new ArrayList<>();
        aList.add(new A("1", 1));
        aList.add(new A("2", 2));
        aList.add(new A("3", 18));
        aList.add(new A("4", 19));

//        List<B>  bList = new ArrayList<>();
        aList.add(new B("_1", 1));
        aList.add(new B("_2", 20 ));
        aList.add(new B("_3", 3));

        aList = doSomethings(aList);
        System.out.println(aList);
    }

    private static List doSomethings(List list) {
        list = (List) list.stream().filter(t -> {
            try {
                final Method getAgeMethod = t.getClass().getMethod("getAge", null);
                final Integer age = (Integer) getAgeMethod.invoke(t, null);
                if (age > 17) {
                    return true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return false;
        }).collect(Collectors.toList());

        try {
            new Runnable() {

                @Override
                public void run() {

                }
            };
        }catch (Exception e) {}
        return list;
    }
}

class A {

    String name;

    Integer age;

    public A(String name, Integer age) {
        this.name = name;
        this.age = age;
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
}


class B {

    String firstName;

    Integer age;

    public B(String firstName, Integer age) {
        this.firstName = firstName;
        this.age = age;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}