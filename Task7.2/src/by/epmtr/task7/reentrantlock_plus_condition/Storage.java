package by.epmtr.task7.reentrantlock_plus_condition;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Storage {
	private static final int CAPACITY = 10;
	private final Queue<Integer> queue = new LinkedList<Integer>(); // количество товара будем хранить в очереди
	private final Random theRandom = new Random();
	
	private final Lock aLock = new ReentrantLock(); // Создаем объект Lock для синхронизации доступа потоков
	private final Condition bufferNotFull = aLock.newCondition(); // Получаем объект Condition, проверка на то, что хранилище не переполнено
	private final Condition bufferNotEmpty = aLock.newCondition(); // Получаем объект Condition, проверка на то, что хранилище не пусто

	public void put() throws InterruptedException {
		aLock.lock(); // блокируем доступ к хранилищу
		try {
			while (queue.size() == CAPACITY) { // если хранилище заполнено
				System.out.println(Thread.currentThread().getName() + " : Buffer is full, waiting");
				bufferNotEmpty.await(); // ждем пока хранилище не освободится
			}

			int number = theRandom.nextInt(100); 
			boolean isAdded = queue.offer(number); 
			if (isAdded) {
				System.out.printf("%s added %d into queue %n", Thread.currentThread().getName(), number);

				System.out.println(Thread.currentThread().getName() + " : Signalling that buffer is no more empty now");
				bufferNotFull.signalAll(); // сигнал всем потребителям, что хранилище больше не пусто
			}
		} finally {
			aLock.unlock(); // снимаем блокировку
		}
	}

	public void get() throws InterruptedException {
		aLock.lock();
		try {
			while (queue.size() == 0) { // если хранилище пусто
				System.out.println(Thread.currentThread().getName() + " : Buffer is empty, waiting");
				bufferNotFull.await(); // ждем пока хранилище не заполнится 
			}

			Integer value = queue.poll();
			if (value != null) {
				System.out.printf("%s consumed %d from queue %n", Thread.currentThread().getName(), value);

				System.out.println(Thread.currentThread().getName() + " : Signalling that buffer may be empty now");
				bufferNotEmpty.signalAll(); // сигнал всем поставщикам, что в хранилище появилось свободное место
			}

		} finally {
			aLock.unlock();
		}
	}
}
