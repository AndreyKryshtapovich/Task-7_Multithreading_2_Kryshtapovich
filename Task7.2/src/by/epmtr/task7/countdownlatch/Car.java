package by.epmtr.task7.countdownlatch;

import java.util.concurrent.CountDownLatch;

public class Car implements Runnable {
	private int carNumber;
	private int carSpeed;// ���������� �������� � ���������� ���������
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
			System.out.printf("���������� �%d �������� � ��������� ������.\n", carNumber);
			// ���������� � ������ - ������� ���������
			// ��������� ������� �� 1
			start.countDown();
			// ����� await() ��������� �����, ��������� ���, �� ��� ���,
			// ����
			// ������� CountDownLatch �� ������ ����� 0
			start.await();
			Thread.sleep(trackLength / carSpeed);// ���� ���� ������� ������
			System.out.printf("���������� �%d �����������!\n", carNumber);
		} catch (InterruptedException e) {
		}
	}
}
