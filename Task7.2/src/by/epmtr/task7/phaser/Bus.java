package by.epmtr.task7.phaser;

import java.util.ArrayList;
import java.util.concurrent.Phaser;
/**
 * @author Andrey
 *������ ������������� Phaser.���� ���� ���������. �� ������ ������� �� ��� ����� ������ ��������� � ����� ��������.
 *������� �������� �� ����� � ��������������� �� ������ ��������� �� ��������� �����. ����� �������� ��������� ������� 
 *���� � ����. ��� ����� ������� ���������� � �������� �� �� ������ ����������.
 */
public class Bus {
    private static final Phaser PHASER = new Phaser(1);// ������� ������ Phaser � ����� ����������� 1 ����� - �������
    //���� 0 � 6 - ��� ���������� ����, 1 - 5 ���������

    public static void main(String[] args) throws InterruptedException {
        ArrayList<Passenger> passengers = new ArrayList<>();

        for (int i = 1; i < 5; i++) {           //����������� ���������� �� ����������
            if ((int) (Math.random() * 2) > 0)
                passengers.add(new Passenger(i, i + 1,PHASER));//���� �������� ������� �� ���������

            if ((int) (Math.random() * 2) > 0)
                passengers.add(new Passenger(i, 5,PHASER));    //���� �������� ������� �� ��������
        }

        for (int i = 0; i < 7; i++) {
            switch (i) {
                case 0:
                    System.out.println("������� ������ �� �����.");
                    PHASER.arrive();//� ���� 0 ����� 1 �������� - �������, ��� �� ����� ����� ������� ������ ������
                    break;
                case 6:
                    System.out.println("������� ����� � ����.");
                    PHASER.arriveAndDeregister();//������� ������� �����, ������ ������. ��� ���� ���������� ��������.
                    break;
                default:
                    int currentBusStop = PHASER.getPhase();
                    System.out.println("��������� � " + currentBusStop);

                    for (Passenger p : passengers)          //���������, ���� �� ��������� �� ���������
                        if (p.getDeparture() == currentBusStop) {
                            PHASER.register();//������������ �����, ������� ����� ����������� � �����. ������� ������� � �������
                            p.start();        // � ���������
                        }
                    PHASER.arriveAndAwaitAdvance();//�������� � ����� ����������
            }
        }
    }
}
