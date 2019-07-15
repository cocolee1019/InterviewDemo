package chapter4;

/**
 *
 *	泛型在Class中的使用。
 *	写法：
 *		Class<T> class;
 *	使用泛型的好处在于在编译期便可以发现不恰当类型的错误。
 *
 *	1、使用Class<?>表示不限定类型，所以一般在不知道类型时，
 *	      可以使用Class<?>而不是使用Class，这样做的好处是，
 *	  “我不是因为疏漏而忽略了类型，而是我确实不知道应该要用哪个类型”。
 *
 *	2、使用Class<? extends T>限定范围，表示只能获取T或T子类的class引用。
 *
 *	3、使用Class<? super T>限定范围，表示只能获取T的父类。
 *		~：Class<? super T>要求右值不能含有模凌两可的值，
 *		     如Class<?>的引用、Class<? extends T>的引用。详见demo4
 *	
 *	4、当在Class中使用泛型时，clazz.newInstance()会返回确切的类型。
 */
public class Course2_GenericInClass {

	public static void main(String[] args) throws InstantiationException, IllegalAccessException {
		
		//以下写法报错：HelloWorldTopSupper为HelloWorld的父类
//		 Class<? extends HelloWorld> clazz3 = HelloWorldSuperClass.class;
		
		//正确写法, HelloWorldImpl是HelloWorld的子类 
		Class<? extends HelloWorld> clazz1 = HelloWorldImpl.class;
		
		//demo2
		Class<? extends HelloWorldTopSupper> clazz2 = HelloWorldImpl.class;
		
		//HelloWorldSuperClass是HelloWorldTopSupper的子类
		Class<? super HelloWorldSuperClass> superClazz3 = HelloWorldTopSupper.class;
		
		//demo3：superClazz34.newInstance()生成的对象为HelloWorldImpl，而不是Object。
		Class<HelloWorldImpl> superClazz34 = HelloWorldImpl.class;
		
		//demo4
		Class<HelloWorldSuperClass> clazzDemo = HelloWorldSuperClass.class;
		Class<? super HelloWorldSuperClass> clazz4 = clazzDemo.getSuperclass();
		
	}
	
	
}