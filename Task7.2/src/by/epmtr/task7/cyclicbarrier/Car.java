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
            System.out.printf("Автомобиль №%d подъехал к паромной переправе.\n", carNumber);
            barrier.await();// метод await() указывает, что поток достиг барьера. 
             				//После этого поток блокируется и ждет пока остальные потоки достигнут барьера.
            System.out.printf("Автомобиль №%d продолжил движение.\n", carNumber);
        } catch (Exception e) {
        }
    }
}
