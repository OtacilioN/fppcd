package school.cesar.ppcd.av1.ex1.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class SharedCounterServerRunnable implements Runnable {
	private Socket client;
	private static int counter = 0;
	private static final byte[] INC = new byte[] { 'I' };
	private DataOutputStream dataOutputStream;
	private DataInputStream dataInputStream;

	private synchronized int getAndIncrement() {
		return SharedCounterServerRunnable.counter++;
	}

	public SharedCounterServerRunnable(Socket client) {
		try {
			this.client = client;
			this.dataInputStream = new DataInputStream(client.getInputStream());
			this.dataOutputStream = new DataOutputStream(client.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void run() {
		try {
			while (true) {
				if (this.dataInputStream.read(INC) == 1) {
					int value = this.getAndIncrement();
					this.dataOutputStream.writeInt(value);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
