package by.epmtr.task7.cyclicbarrier;

import java.util.concurrent.CyclicBarrier;

public class Car implements Runnable {
    private int carNumber;
    private final CyclicBarrier barrier;

    public Car(int carNumber, CyclicBarrier barrier) {
        this.carNumber = carNumber;
        this.barrier = barrier;
    }

    @Override
    public void run() {
        try {
            System.out.printf("���������� �%d �������� � �������� ���������.\n", carNumber);
            barrier.await();// ����� await() ���������, ��� ����� ������ �������. 
             				//����� ����� ����� ����������� � ���� ���� ��������� ������ ��������� �������.
            System.out.printf("���������� �%d ��������� ��������.\n", carNumber);
        } catch (Exception e) {
        }
    }
}
