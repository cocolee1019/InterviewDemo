package chapter4;

/**
 * JDK的动态代理
 * 实现步骤：
 * 	1、JDK的动态代理，必需要实现InvocationHandler接口。
 * 	2、建立代理对象与真实对象的关系，在本例中，通过bind方法进行绑定。
 * 	3、实现代理逻辑的方法， 在本例中，代理逻辑实现在invoke中。
 */
public class Course3_Proxy {

	public static void main(String[] args) {
		JdkProxyExample jdk = new JdkProxyExample();
		HelloWorld proxy = (HelloWorld) jdk.bind(new HelloWorldImpl());
		proxy.sayHello();
		System.out.println(proxy.toString());
	}
}
