package by.epmtr.task7.reentrantlock_plus_condition;

public class Consumer extends Thread {
	private Storage pc;

	public Consumer(Storage sharedObject) {
		super("CONSUMER");
		this.pc = sharedObject;
	}

	@Override
	public void run() {
		try {
			pc.get();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
