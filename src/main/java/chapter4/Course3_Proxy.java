package chapter4;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * JDK的动态代理
 * 实现步骤：
 * 	1、JDK的动态代理，必需要实现InvocationHandler接口。
 * 	2、建立代理对象与真实对象的关系，在本例中，通过bind方法进行绑定。
 * 	3、实现代理逻辑的方法， 在本例中，代理逻辑实现在invoke中。
 *
 * 	2021-07-21 补充
 * 	1、静态代理与动态代理区别。
 *     	静态代理的关键在于代理类与被代理类需要实现同一接口或继承同一父类。代理类持有被代理类的实现，并添加某些功能。
 *		动态代理增强的内容可以动态设置。
 *
 * 	2、动态代理实现方式有哪几种，原理是什么？
 *
 */
public class Course3_Proxy {

	public static void main(String[] args) {
		JdkProxyExample jdk = new JdkProxyExample();
		HelloWorld proxy = (HelloWorld) jdk.bind(new HelloWorldImpl());
		proxy.sayHello();
		System.out.println(proxy);

		System.out.println(LocalDate.now().format(DateTimeFormatter.ISO_DATE));
	}
}
