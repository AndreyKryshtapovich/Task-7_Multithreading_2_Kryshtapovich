package by.epmtr.task7.countdownlatch;

import java.util.concurrent.CountDownLatch;
/**
 * 
 * @author Andrey
 * Пример использования CountDownLatch. Проводит автомобильная гонка. В гонке принимают участие пять автомобилей. 
 * Для начала гонки нужно, чтобы выполнились следующие условия:
 * 1)Каждый из пяти автомобилей подъехал к стартовой прямой;
 * 2)Была дана команда «На старт!»;
 * 3)Была дана команда «Внимание!»;
 * 4)Была дана команда «Марш!».
 * Важно, чтобы все автомобили стартовали одновременно.
 */
public class Race {
	// Создаем объект CountDownLatch на 8 "условий"
	private static final CountDownLatch START = new CountDownLatch(8);
	// Задаем длину гоночной трассы 
	private static final int trackLength = 500000;

	public static void main(String[] args) throws InterruptedException {
		// Создаем 5 гоночных автомобилей
		for (int i = 1; i <= 5; i++) {
			new Thread(new Car(i, (int) (Math.random() * 100 + 50), START, trackLength)).start();
			Thread.sleep(1000);
		}

		while (START.getCount() > 3) // Если автомобиль стоит на старте, он вызовет метод countDown(),
			                         //уменьшив тем самым значение счетчика
			Thread.sleep(100); // Если автомобиль еще не собрадись - ждем

		Thread.sleep(1000);
		System.out.println("На старт!");
		START.countDown();// Команда дана, уменьшаем счетчик на 1
		Thread.sleep(1000);
		System.out.println("Внимание!");
		START.countDown();// Команда дана, уменьшаем счетчик на 1
		Thread.sleep(1000);
		System.out.println("Марш!");
		START.countDown();// Команда дана, уменьшаем счетчик на 1
		// счетчик становится равным нулю, и все ожидающие потоки
		// одновременно разблокируются
	}

}
