package by.epmtr.task7.exchanger;

import java.util.concurrent.Exchanger;

public class Truck implements Runnable {
    private int number;
    private String dep;
    private String dest;
    private String[] parcels;
    private final Exchanger<String> exchanger;

    public Truck(int number, String departure, String destination, String[] parcels, Exchanger<String> exchanger) {
        this.number = number;
        this.dep = departure;
        this.dest = destination;
        this.parcels = parcels;
        this.exchanger = exchanger;
    }

    @Override
    public void run() {
        try {
            System.out.printf("� �������� �%d ���������: %s � %s.\n", number, parcels[0], parcels[1]);
            System.out.printf("�������� �%d ������ �� ������ %s � ����� %s.\n", number, dep, dest);
            Thread.sleep(1000 + (long) Math.random() * 5000);
            System.out.printf("�������� �%d ������� � ����� �.\n", number);
            parcels[1] = exchanger.exchange(parcels[1]);//��� ������ exchange() ����� ����������� � ����
            //���� ������ ����� ������� exchange(), ����� ����� ���������� ����� �����������, ����������� � ����� 
            //������ �� �������.
            System.out.printf("� �������� �%d ����������� ������� ��� ������ %s.\n", number, dest);
            Thread.sleep(1000 + (long) Math.random() * 5000);
            System.out.printf("�������� �%d ������� � %s � ��������: %s � %s.\n", number, dest, parcels[0], parcels[1]);
        } catch (InterruptedException e) {
        }
    }
}
