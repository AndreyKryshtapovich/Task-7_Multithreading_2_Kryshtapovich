package by.epamtr.task7.entity;

import java.util.concurrent.Semaphore;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epamtr.task7.helper.StringConstants;

public class Philosopher extends Thread {
	private Chopstick leftChopstick;
	private Chopstick rightChopstick;
	private int count = 0; // счетчик для отслеживания того, сколько раз философ поел
	private final Semaphore semaphore; // Semaphore для синхронизации
	private final static Logger rootLogger = LogManager.getRootLogger();
	

	public Philosopher(Chopstick left, Chopstick right, Semaphore semaphore) {
		leftChopstick = left;
		rightChopstick = right;
		this.semaphore = semaphore;
	}

	public void eat()  {
		try {
			semaphore.acquire(); //запрашиваем у семафора рзрешение попытаться заблокировать вилки
		} catch (InterruptedException e) {
			rootLogger.error(e.getMessage());
		}
		if (! leftChopstick.isUsed()) { // если левая вилка еще не занята
			 leftChopstick.take(); // блокируем левуб вилку
			 
			if (! rightChopstick.isUsed()) { // если правая вилка еще не занята
				rightChopstick.take(); // блокируем правую вилку

				rootLogger.info(Thread.currentThread().getName() + StringConstants.eat);
				count++; // успешно заблокированы 2 вилки и филосов поел
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					rootLogger.error(e.getMessage());
				}
				rightChopstick.release(); // освободить правую вилку
			}
			leftChopstick.release(); // освободить левую вилку
		}
		semaphore.release(); // возвращаем разрешение семафору назад
		think();
	}

	public void think() {
		rootLogger.info(Thread.currentThread().getName() + StringConstants.think);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			rootLogger.error(e.getMessage());
		}
	}

	public void run() {
		for (int i = 0; i < 11; i++) { // цикл работы для каждого философа конечен для отслеживания результатов
			eat();
		}
	}
	public int getCount(){
		return count;
	}
}
