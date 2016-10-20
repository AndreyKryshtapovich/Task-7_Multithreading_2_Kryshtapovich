package by.epmtr.task7.semaphore;

import java.util.concurrent.Semaphore;

public  class Car implements Runnable {
    private int carNumber;
    private  final Semaphore semaphore;
    private  final boolean[] parkingPlaces;

    public Car(int carNumber, Semaphore semaphore, boolean[] parking_places) {
        this.carNumber = carNumber;
        this.semaphore = semaphore;
        this.parkingPlaces = parking_places;
    }

    @Override
    public void run() {
        System.out.printf("���������� �%d �������� � ��������.\n", carNumber);
        try {
            // ����� acquire() ����������� ������ � ����, ����������
        	// �� ������� ������, ���� ������ �� ��������, ����� ����� ������������ � ��������
        	// ���������� ��������
            semaphore.acquire();

            int parkingNumber = -1;

            //���� ��������� ����� � ���������. 
            // synchronized ���� ��������� ��� ������������� ������ �������,������� ������� ��� ����������.
            synchronized (parkingPlaces){
                for (int i = 0; i < 5; i++){
                    if (!parkingPlaces[i]) {      //���� ����� ��������
                        parkingPlaces[i] = true;  //�������� ���
                        parkingNumber = i;         //������� ���������� �����, ����������� �������
                        System.out.printf("���������� �%d ������������� �� ����� %d.\n", carNumber, i);
                        break;
                    }
                }
            }

            Thread.sleep(5000);  // ������ �� ��������

            synchronized (parkingPlaces) {
            	parkingPlaces[parkingNumber] = false;//����������� �����
            }
            
            // ����������� �����, ���������� ��� �������� � ������� ������ release()
            semaphore.release();
            System.out.printf("���������� �%d ������� ��������.\n", carNumber);
        } catch (InterruptedException e) {
        }
    }
}