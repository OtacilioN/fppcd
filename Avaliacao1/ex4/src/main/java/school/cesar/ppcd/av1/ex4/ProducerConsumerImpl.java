package school.cesar.ppcd.av1.ex4;

import java.util.Queue;
import java.util.LinkedList;

public class ProducerConsumerImpl implements ProducerConsumer {
	Queue<SomeRequest> buffer = new LinkedList<SomeRequest>();
	final int MAX_BUFFER_LEN = 2;

	public void produce(SomeRequest request) {
		synchronized (buffer) {
			boolean isBufferFull = buffer.size() >= MAX_BUFFER_LEN;
			if (isBufferFull) {
				try {
					buffer.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} else {
				buffer.add(request);
				buffer.notify();
			}
		}
	}

	public SomeRequest consume() {
		SomeRequest request;
		synchronized (buffer) {
			while (buffer.isEmpty()) {
				try {
					buffer.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			request = buffer.remove();
			buffer.notify();
			return request;
		}
	}
}
