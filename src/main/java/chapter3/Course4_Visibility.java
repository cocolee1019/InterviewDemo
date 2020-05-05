package chapter3;

/**
 * 可见性测试：使用volatile
 * 轻量级同步、保证可见性与禁止指令重排。
 * 
 */
public class Course4_Visibility {

	//1、如果此处不使用volatile，循环将不会结束。
	boolean x = true;
	
	public void stop() {
		this.x = false;
	}
	
	
	public static void main(String[] args) throws InterruptedException {
		
		Course4_Visibility c = new Course4_Visibility();
		
		Thread t1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				int i = 100;
				while(c.x) {
					i++;
				}
				System.out.println("线程停止" + i);
			
			}
		});
		
		Thread t2 = new Thread(()->{
			System.out.println(c.x);
		}); 

		t1.start();
		Thread.sleep(1000);
		c.stop();
		System.out.println("主线程停止");
		t2.start();
	}
	
}
