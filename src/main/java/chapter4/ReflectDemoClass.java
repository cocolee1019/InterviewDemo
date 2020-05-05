package chapter4;

public class ReflectDemoClass {

	public ReflectDemoClass() {
		System.out.println("我是ReflectDemoClass， 我被构造了。");
	}
	
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
