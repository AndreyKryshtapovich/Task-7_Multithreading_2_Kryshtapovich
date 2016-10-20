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
        System.out.println(this + " ждёт на остановке № " + this.departure);
        this.phaser = phaser;
    }

    @Override
    public void run() {
        try {
            System.out.println(this + " сел в автобус.");

            while (phaser.getPhase() < destination){ //Пока автобус не приедет на нужную остановку(фазу)
                phaser.arriveAndAwaitAdvance();     //заявляем в каждой фазе о готовности и ждем
            }
            Thread.sleep(1);
            System.out.println(this + " покинул автобус.");
            phaser.arriveAndDeregister();   //Отменяем регистрацию на нужной фазе
        } catch (InterruptedException e) {
        }
    }

    @Override
    public String toString() {
        return "Пассажир{" + departure + " -> " + destination + '}';
    }
}