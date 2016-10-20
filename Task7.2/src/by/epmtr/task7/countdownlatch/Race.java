package by.epmtr.task7.countdownlatch;

import java.util.concurrent.CountDownLatch;
/**
 * 
 * @author Andrey
 * ������ ������������� CountDownLatch. �������� ������������� �����. � ����� ��������� ������� ���� �����������. 
 * ��� ������ ����� �����, ����� ����������� ��������� �������:
 * 1)������ �� ���� ����������� �������� � ��������� ������;
 * 2)���� ���� ������� ��� �����!�;
 * 3)���� ���� ������� ���������!�;
 * 4)���� ���� ������� �����!�.
 * �����, ����� ��� ���������� ���������� ������������.
 */
public class Race {
	// ������� ������ CountDownLatch �� 8 "�������"
	private static final CountDownLatch START = new CountDownLatch(8);
	// ������ ����� �������� ������ 
	private static final int trackLength = 500000;

	public static void main(String[] args) throws InterruptedException {
		// ������� 5 �������� �����������
		for (int i = 1; i <= 5; i++) {
			new Thread(new Car(i, (int) (Math.random() * 100 + 50), START, trackLength)).start();
			Thread.sleep(1000);
		}

		while (START.getCount() > 3) // ���� ���������� ����� �� ������, �� ������� ����� countDown(),
			                         //�������� ��� ����� �������� ��������
			Thread.sleep(100); // ���� ���������� ��� �� ��������� - ����

		Thread.sleep(1000);
		System.out.println("�� �����!");
		START.countDown();// ������� ����, ��������� ������� �� 1
		Thread.sleep(1000);
		System.out.println("��������!");
		START.countDown();// ������� ����, ��������� ������� �� 1
		Thread.sleep(1000);
		System.out.println("����!");
		START.countDown();// ������� ����, ��������� ������� �� 1
		// ������� ���������� ������ ����, � ��� ��������� ������
		// ������������ ��������������
	}

}
