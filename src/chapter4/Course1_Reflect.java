package chapter4;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 反射：
 * 		1、Class对象。
 * 		    Class对象是反射的基础，该对象没有public构造器，获得Class实例有三种方式：
 * 			obj.getClass()、Clazz.class（类字面常量）、Class.forName("");
 * 			Class对象的创建是虚拟机在加载对象时， 自动调用类加载器的defineClass方法创建。
 * 
 * 		2、利用Class对象调用私有方法。callPrivate();
 * 		  使用newInstance()方法时，对应的Class对象需要提供默认的构造方法。
 * 		
 * 		3、获得基本类型（primitive type）的Class对象。  ==> primitiveTypeDemo();
 * 
 * 注意：
 * 		1、使用类字面常量（Clazz.class）获取class时，不会初始化class。
 * 		2、Class.forName("")会立即初始化。    
 * 			1、2证明在classInitDemo()
 * 		3、类初始化的过程中，如果static final变量的值是一个编译期常量，引用该变量，不会使程序初始，
 * 		      如果该值非编译期常量，引用该变量，则会引起初始化。
 * 	
 */			
public class Course1_Reflect {

	public static void main(String[] args) throws IllegalArgumentException, InvocationTargetException, InstantiationException, IllegalAccessException {
		callPrivate();
//		primitiveTypeDemo();
//		classInitDemo();
	}
	
	/**
	 * 	2、利用Class对象调用私有方法。
	 * 	obj.getDeclaredMethods(); //获得声明的方法
	 */
	private static void callPrivate() throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		try {
			Class<?> clazz = Class.forName("chapter4.ReflectDemoClass");
			Object obj = clazz.newInstance();
			for(Method method : clazz.getDeclaredMethods()) {
				if(method.getParameterCount() == 0) {
					method.setAccessible(true);
					method.invoke(obj);
				}else if(method.getParameterCount() == 1) {
					method.setAccessible(true);
					method.invoke(obj, "i am a pig");
				}
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	private static void primitiveTypeDemo() {
		Class<?> clazz = Boolean.TYPE;  //等价于Boolean.TYPE
		Class<?> voidClazz = void.class;   //等价于Void.TYPE
		
		System.out.println(clazz.getCanonicalName());
		for (Method m : clazz.getMethods()) {
			System.out.println(m);
		}
	}
	
	private static void classInitDemo() {
		
		Class<?> clazz = ReflectDemoClass.class;
		System.out.println("ReflectDemoClass类未被加载？   yes");
		
		try {
			Class<?> clazz2 = Class.forName("chapter4.ReflectDemoClass2");
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("ReflectDemoClass2类未被加载？   no");
	}
}
