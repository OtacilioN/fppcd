package school.cesar.ppcd.av1.ex2;

public class Main {

	public static void main(String[] args) {
		Object mutex = new Object();
		FakeLongTask fakeLongTask = new FakeLongTask(mutex);
		Thread threadFakeLongTask = new Thread(fakeLongTask);
		threadFakeLongTask.start();

		System.out.println("waiting...");
		synchronized (mutex) {
			try {
				mutex.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("done!");
		}
	}
}
