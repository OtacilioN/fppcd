package school.cesar.ppcd.av1.ex2;

import static school.cesar.ppcd.av1.ex2.Util.nap;

public class FakeLongTask implements Runnable {
	private static final long ONE_MINUTE = 60 * 1000;
	private Object mutex;

	public FakeLongTask(Object mutex) {
		this.mutex = mutex;
	}

	public void run() {
		try {
			nap(ONE_MINUTE);
			synchronized (mutex) {
				mutex.notify();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
