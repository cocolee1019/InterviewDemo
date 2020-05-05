package chapter4;

public class HelloWorldImpl extends HelloWorldSuperClass implements HelloWorld{

	@Override
	public void sayHello() {
		System.out.println("hello world");
	}
	
}
