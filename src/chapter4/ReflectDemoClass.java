package chapter4;

public class ReflectDemoClass {

	static {
		System.out.println("啊~我被引用啦。");
	}
	
	private void sayHello() {
		System.out.println("hello");
	}
	
	private void sayMsg(String msg) {
		System.out.println(msg);
	}
}
