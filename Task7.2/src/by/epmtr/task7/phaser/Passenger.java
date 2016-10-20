package by.epmtr.task7.phaser;

import java.util.concurrent.Phaser;

public class Passenger extends Thread {
    private int departure;
    private int destination;
    public int getDeparture() {
		return departure;
	}

	public int getDestination() {
		return destination;
	}

	private final Phaser phaser;

    public Passenger(int departure, int destination, Phaser phaser) {
        this.departure = departure;
        this.destination = destination;
        System.out.println(this + " ��� �� ��������� � " + this.departure);
        this.phaser = phaser;
    }

    @Override
    public void run() {
        try {
            System.out.println(this + " ��� � �������.");

            while (phaser.getPhase() < destination){ //���� ������� �� ������� �� ������ ���������(����)
                phaser.arriveAndAwaitAdvance();     //�������� � ������ ���� � ���������� � ����
            }
            Thread.sleep(1);
            System.out.println(this + " ������� �������.");
            phaser.arriveAndDeregister();   //�������� ����������� �� ������ ����
        } catch (InterruptedException e) {
        }
    }

    @Override
    public String toString() {
        return "��������{" + departure + " -> " + destination + '}';
    }
}