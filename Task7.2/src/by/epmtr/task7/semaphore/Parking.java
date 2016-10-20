package by.epmtr.task7.semaphore;

import java.util.concurrent.Semaphore;
/**
 * 
 * @author Andrey
 *������ ������������� Semaphore. ���������� ��������, ������� ������������ ����� ������� �� ����� 5 �����������. 
 *���� �������� ��������� ���������, �� ����� ��������� ���������� ������ ��������� ���� �� ����������� ���� �� ���� �����.
 * ����� ����� �� ������ ��������������.
 */
public class Parking {
	private  final static boolean[] PARKING_PLACES = new boolean[5]; //true - ����� ������, false - ��������
	// ������ �������� ������������ ������ "������������" �������.
	// ����� acquire() ����� ������ ���������� � ������� �������
	private  final static Semaphore SEMAPHORE = new Semaphore(5, true);
	public static void main(String[] args) throws InterruptedException {
        for (int i = 1; i <= 7; i++) {
           new Thread(new Car(i,SEMAPHORE,PARKING_PLACES)).start();
            Thread.sleep(400);
        }
    }
	
	
}
