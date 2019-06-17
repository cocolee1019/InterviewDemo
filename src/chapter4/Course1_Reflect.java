package chapter4;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 反射：
 * 		1、Class对象。
 * 		    Class对象是反射的基础，该对象没有public构造器，获得Class实例有三种方式：
 * 			obj.getClass()、Clazz.class、Class.forName("");
 * 			Class对象的创建是虚拟机在加载对象时， 自动调用类加载器的defineClass方法创建。
 * 
 * 		2、利用Class对象调用私有方法。callPrivate();
 */
public class Course1_Reflect {

	public static void main(String[] args) throws IllegalArgumentException, InvocationTargetException, InstantiationException, IllegalAccessException {
		callPrivate();
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
}
