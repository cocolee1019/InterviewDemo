package chapter1;

/**
 * final的使用:
 * 1、用于修饰类：使类无法被继承
 * 2、用于修饰方法：使子类无法重写
 * 3、用于修饰属性：使用属性在初始化后无法被修改
 */
public class Course2_Final {



}


class ParentClass {
    public final void doSomethings() {
        //do
    }
}

class SubClass extends ParentClass {

//    public void doSomethings() {
//        //do
//    }
}
