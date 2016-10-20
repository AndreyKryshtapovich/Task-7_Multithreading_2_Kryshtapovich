package by.epmtr.task7.cyclicbarrier;

import java.util.concurrent.CyclicBarrier;
/**
 * 
 * @author Andrey
 * Пример использования CyclicBarrier.  Существует паромная переправа. Паром может переправлять одновременно по три автомобиля.
 * Чтобы не гонять паром лишний раз, нужно отправлять его, когда у переправы соберется минимум три автомобиля.
 * CyclicBarrier похож на CountDownLatch, но он может быть повторно использован + есть возможность задать метод,
 * который будет выполняться при открытии барьера. 
 */
public class Ferry {
    private static final CyclicBarrier BARRIER = new CyclicBarrier(3, new FerryBoat());
    //Создаем объект CyclicBarrier на три потока и инициализируем его таском, который будет выполняться, когда
    //у барьера соберется три потока. После выполнения таска потоки будут освобождены.

    // 9 машин будут переправляться через реку на пароме.
    public static void main(String[] args) throws InterruptedException {
    	for (int i = 0; i < 9; i++) {
            new Thread(new Car(i, BARRIER)).start();
            Thread.sleep(400);
        }
    }
}
