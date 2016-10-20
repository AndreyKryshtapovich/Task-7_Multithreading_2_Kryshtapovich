package by.epamtr.task7.entity;

import java.util.concurrent.Semaphore;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epamtr.task7.helper.StringConstants;

public class Philosopher extends Thread {
	private Chopstick leftChopstick;
	private Chopstick rightChopstick;
	private int count = 0; // ������� ��� ������������ ����, ������� ��� ������� ����
	private final Semaphore semaphore; // Semaphore ��� �������������
	private final static Logger rootLogger = LogManager.getRootLogger();
	

	public Philosopher(Chopstick left, Chopstick right, Semaphore semaphore) {
		leftChopstick = left;
		rightChopstick = right;
		this.semaphore = semaphore;
	}

	public void eat()  {
		try {
			semaphore.acquire(); //����������� � �������� ��������� ���������� ������������� �����
		} catch (InterruptedException e) {
			rootLogger.error(e.getMessage());
		}
		if (! leftChopstick.isUsed()) { // ���� ����� ����� ��� �� ������
			 leftChopstick.take(); // ��������� ����� �����
			 
			if (! rightChopstick.isUsed()) { // ���� ������ ����� ��� �� ������
				rightChopstick.take(); // ��������� ������ �����

				rootLogger.info(Thread.currentThread().getName() + StringConstants.eat);
				count++; // ������� ������������� 2 ����� � ������� ����
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					rootLogger.error(e.getMessage());
				}
				rightChopstick.release(); // ���������� ������ �����
			}
			leftChopstick.release(); // ���������� ����� �����
		}
		semaphore.release(); // ���������� ���������� �������� �����
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
		for (int i = 0; i < 11; i++) { // ���� ������ ��� ������� �������� ������� ��� ������������ �����������
			eat();
		}
	}
	public int getCount(){
		return count;
	}
}
