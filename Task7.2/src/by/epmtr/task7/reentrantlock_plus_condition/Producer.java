package by.epmtr.task7.reentrantlock_plus_condition;

public class Producer extends Thread {
	private Storage pc;
	

	public Producer(Storage sharedObject) {
		super("PRODUCER");
		this.pc = sharedObject;
	}

	@Override
	public void run() {
		try {
			pc.put();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
