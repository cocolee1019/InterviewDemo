package chapter4;

public class Main {

	public static void main(String[] args) {
		JdkProxyExample jdk = new JdkProxyExample();
		HelloWorld proxy = (HelloWorld) jdk.bind(new HelloWorldImpl());
		proxy.sayHello();
	}
}
