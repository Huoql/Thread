package thread;
/**
 * 静态方法若使用synchronized修饰后，那么该方法一定
 * 具有同步效果。
 *
 * 关于同步方法的总结：
 * 1.同步方法仍然设计到同步监视器，只是不需要我们显式的声明。
 * 2.非静态的同步方法，同步监视器是：this
 *   静态的同步方法，同步监视器就是当前类本身
 *
 * @author ta
 *
 */
public class SyncDemo3 {
	public static void main(String[] args) {
		Thread t1 = new Thread() {
			public void run() {
				Boo.dosome();
			}
		};
		Thread t2 = new Thread() {
			public void run() {
				Boo.dosome();
			}
		};
		t1.start();
		t2.start();
	}
}

class Boo{
	public synchronized static void dosome() {//同步监视器：Boo.class
		Thread t = Thread.currentThread();
		try {
			System.out.println(t.getName()+":正在运行dosome方法");
			Thread.sleep(5000);
			System.out.println(t.getName()+":运行dosome方法完毕");
		} catch (Exception e) {
		}
	}
}




