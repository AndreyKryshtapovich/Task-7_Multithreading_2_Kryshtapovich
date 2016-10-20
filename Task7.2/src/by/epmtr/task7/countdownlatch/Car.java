package by.epmtr.task7.countdownlatch;

import java.util.concurrent.CountDownLatch;

public class Car implements Runnable {
	private int carNumber;
	private int carSpeed;// автомобиль движется с постоянной скоростью
	private final CountDownLatch start;
	private final int trackLength;

	public Car(int carNumber, int carSpeed, CountDownLatch start, int trackLength) {
		this.carNumber = carNumber;
		this.carSpeed = carSpeed;
		this.start = start;
		this.trackLength = trackLength;
	}

	@Override
	public void run() {
		try {
			System.out.printf("Автомобиль №%d подъехал к стартовой прямой.\n", carNumber);
			// Автомобиль у старта - условие выполнено
			// уменьшаем счетчик на 1
			start.countDown();
			// метод await() блокирует поток, вызвавший его, до тех пор,
			// пока
			// счетчик CountDownLatch не станет равен 0
			start.await();
			Thread.sleep(trackLength / carSpeed);// ждем пока проедет трассу
			System.out.printf("Автомобиль №%d финишировал!\n", carNumber);
		} catch (InterruptedException e) {
		}
	}
}
