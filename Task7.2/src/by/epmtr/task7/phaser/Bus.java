package by.epmtr.task7.phaser;

import java.util.ArrayList;
import java.util.concurrent.Phaser;
/**
 * @author Andrey
 *ѕример использовани€ Phaser.≈сть п€ть остановок. Ќа первых четырех из них могут сто€ть пассажиры и ждать автобуса.
 *јвтобус выезжает из парка и останавливаетс€ на каждой остановке на некоторое врем€. ѕосле конечной остановки автобус 
 *едет в парк. Ќам нужно забрать пассажиров и высадить их на нужных остановках.
 */
public class Bus {
    private static final Phaser PHASER = new Phaser(1);// —оздаем объект Phaser и сразу регистрирум 1 поток - автобус
    //‘азы 0 и 6 - это автобусный парк, 1 - 5 остановки

    public static void main(String[] args) throws InterruptedException {
        ArrayList<Passenger> passengers = new ArrayList<>();

        for (int i = 1; i < 5; i++) {           //—генерируем пассажиров на остановках
            if ((int) (Math.random() * 2) > 0)
                passengers.add(new Passenger(i, i + 1,PHASER));//Ётот пассажир выходит на следующей

            if ((int) (Math.random() * 2) > 0)
                passengers.add(new Passenger(i, 5,PHASER));    //Ётот пассажир выходит на конечной
        }

        for (int i = 0; i < 7; i++) {
            switch (i) {
                case 0:
                    System.out.println("јвтобус выехал из парка.");
                    PHASER.arrive();//¬ фазе 0 всего 1 участник - автобус, ему не нужно ждать никакие другие потоки
                    break;
                case 6:
                    System.out.println("јвтобус уехал в парк.");
                    PHASER.arriveAndDeregister();//—нимаем главный поток, ломаем барьер. ¬се фазы выполнени€ пройдены.
                    break;
                default:
                    int currentBusStop = PHASER.getPhase();
                    System.out.println("ќстановка є " + currentBusStop);

                    for (Passenger p : passengers)          //ѕровер€ем, есть ли пассажиры на остановке
                        if (p.getDeparture() == currentBusStop) {
                            PHASER.register();//–егистрируем поток, который будет участвовать в фазах. ѕасажир садитс€ в автобус
                            p.start();        // и запускаем
                        }
                    PHASER.arriveAndAwaitAdvance();//—ообщаем о своей готовности
            }
        }
    }
}
