package by.epmtr.task7.cyclicbarrier;

import java.util.concurrent.CyclicBarrier;
/**
 * 
 * @author Andrey
 * ������ ������������� CyclicBarrier.  ���������� �������� ���������. ����� ����� ������������ ������������ �� ��� ����������.
 * ����� �� ������ ����� ������ ���, ����� ���������� ���, ����� � ��������� ��������� ������� ��� ����������.
 * CyclicBarrier ����� �� CountDownLatch, �� �� ����� ���� �������� ����������� + ���� ����������� ������ �����,
 * ������� ����� ����������� ��� �������� �������. 
 */
public class Ferry {
    private static final CyclicBarrier BARRIER = new CyclicBarrier(3, new FerryBoat());
    //������� ������ CyclicBarrier �� ��� ������ � �������������� ��� ������, ������� ����� �����������, �����
    //� ������� ��������� ��� ������. ����� ���������� ����� ������ ����� �����������.

    // 9 ����� ����� �������������� ����� ���� �� ������.
    public static void main(String[] args) throws InterruptedException {
    	for (int i = 0; i < 9; i++) {
            new Thread(new Car(i, BARRIER)).start();
            Thread.sleep(400);
        }
    }
}
