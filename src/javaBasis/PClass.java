package javaBasis;

import javaBasis.StaticClassDemo.InnerClass;

/**
 * 内部类的使用:
 * 定义在一个类的内部的类，叫做内部类。访问权限可以是private public protected 默认。
 * 可以声明为abstract类，供其它外部类或者内部类扩展，也可以声明为static和final。
 * 外部类按常规的方式使用内部类，唯一的差别是内部类可以访问所在外部的私有方法和属性。
 * 
 * 1、初始化非静态内部类，在外部类中，创建一个内部类时，需要先实例化内部类所在的外部类对象：
 * InnerClass innerClass = new StaticClassDemo().new InnerClass();
 * 
 * 2、初始化静态内部类：
 * StaticClassDemo.StaticInnerClass staticInnerClass = new StaticClassDemo.StaticInnerClass();
 * 
 * 非静态的与静态的内部类区别：
 * 1、外观类型为非静态类时，不需要加外部类，而静态类需要，因为静态类是依赖于外部类的。
 * 2、非静态类在实例化时，需要先实例化外部类，而静态类则不需要先实例外部类。
 * 3、非静态内部类，可以引用外部类静态与非静态变量或方法，而静态内部类，只能引用外部类的静态方法和静态变量。
 * 4、非静态内部类不可以拥有static字段，而静态类可以拥有。
 * 
 * 问题：
 * 1、当外部类被实例化时，非静态内部类会被加载吗？会被实例化吗？ 静态内部类会加载吗？会被实例化吗？
 * 答：当外部类被实例化时，非静态内部类不会被加载，也不会被实例化。静态内部类也不会被加载，也不会被实例化。
 */
class StaticClassDemo {
	
	int x = 0;
	static int y = 0;
	
	static {
		System.out.println("外部类被加载");
	}
	
	/**
	 * 静态内部类	
	 */
	public static class StaticInnerClass {
		private int id;
		private String name;
		public static int y = 0;
		
		public StaticInnerClass() {
			System.out.println("构造静态StaticInnerClass");
			//静态内部类引用外部类的静态变量,但不能引用非变量
			System.out.println(y);
		}
		
		static {
			System.out.println("内部静态类，被加载");
		}
	}
	
	
	/**
	 * 非静态内部类
	 */
	public class InnerClass {
		private int id;
		private String name;
	
		public InnerClass() {
			System.out.println("构造非静态InnerClass");
			System.out.println(x + "," + y);
		}
		
		int getOutClassX() {
			return x;
		}
	}
	
	public void doSomething() {
		//在类中使用内部类
		InnerClass c = new InnerClass();
		c.id = 10;
		System.out.println(c.id);
		
		StaticInnerClass staticInner = new StaticInnerClass();
	}
}

public class PClass {
	public static void main(String[] args) {
		//构造一个外部类
		StaticClassDemo demo = new StaticClassDemo();
		
		//其它类中，使用内部类，需要先创建内部类中，所在的外部类。
		InnerClass innerClass = new StaticClassDemo().new InnerClass();
		
		//在其它类中，使用静态内部类，不需要创建外部类实例。
		StaticClassDemo.StaticInnerClass staticInner = new StaticClassDemo.StaticInnerClass();
		
		//使用静态内部的静态变量
		System.out.println(StaticClassDemo.StaticInnerClass.y);
	}
}