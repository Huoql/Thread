package thread;
/**
 * 多线程并发安全问题
 * 当多个线程并发操作同一临界资源时（多个线程同时访问一个共享变量），由于线程切换时机不确定
 * 导致操作代码执行顺序混乱，会出现逻辑混乱，严重时可能导致
 * 系统瘫痪。
 * @author ta
 *
 */
public class SyncDemo1 {
	public static void main(String[] args) {
		final Table table = new Table();
		Thread t1 = new Thread() {
			public void run() {
				while(true) {
					int bean = table.getBean();
					Thread.yield();
					System.out.println(getName()+":"+bean);
				}
			}
		};
		Thread t2 = new Thread() {
			public void run() {
				while(true) {
					int bean = table.getBean();
					Thread.yield();
					System.out.println(getName()+":"+bean);
				}
			}
		};
		t1.start();
		t2.start();
	}
}

class Table{
	//桌子上有20个豆子
	private int beans = 20;
	/**
	 * 当一个方法被synchronized修饰后，那么这个方法称为
	 * “同步方法”，即:多个线程不能同时执行方法内部
	 * 这种将多个线程并发运行改为有序的先后运行方法，可以
	 * 有效的解决并发安全问题。
	 * 就是将各干各的改变为排队执行。
	 * @return
	 */
	public synchronized int getBean() {//同步监视器就是this
		if(beans==0) {
			throw new RuntimeException("没有豆子了!");
		}
		Thread.yield();//模拟CPU时间片用完了，发生切换
		return beans--;
	}
}








