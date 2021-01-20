package thread;
/**
 * 当一个线程正在处于阻塞状态时，若被其他线程调用中断方法
 * 试图中断时就会抛出中断异常，并结束阻塞状态。注意，此时
 * 线程并没有真的被中断，只是打断了睡眠阻塞。
 * @author ta
 *
 */
public class SleepDemo2 {
	public static void main(String[] args) {
		Thread lin = new Thread() {
			public void run() {
				System.out.println("林:刚美完容~睡一会儿吧~");
				try {
					Thread.sleep(1000000);
				} catch (InterruptedException e) {
					System.out.println("林:干嘛呢!干嘛呢!干嘛呢!都破了相了!");
				}
				System.out.println("林:醒了!");
			}			
		};
		
		Thread huang = new Thread() {
			public void run() {
				System.out.println("黄:开始砸墙!");
				for(int i=0;i<5;i++) {
					System.out.println("黄:80!");
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
					}
				}
				System.out.println("咣当！");
				System.out.println("黄:搞定!");
				//中断lin线程的睡眠阻塞
				lin.interrupt();
			}
		};
		
		lin.start();
		huang.start();
	}
}








