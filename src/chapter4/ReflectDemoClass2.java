package chapter4;

public class ReflectDemoClass2 {

	static {
		System.out.println("我是ReflectDemoClass2，  我被引用啦。");
	}
	
	private void sayHello() {
		System.out.println("hello");
	}
	
	private void sayMsg(String msg) {
		System.out.println(msg);
	}
}
