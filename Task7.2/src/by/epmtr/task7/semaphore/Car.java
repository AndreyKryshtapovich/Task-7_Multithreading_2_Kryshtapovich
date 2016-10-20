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
        System.out.printf("Автомобиль №%d подъехал к парковке.\n", carNumber);
        try {
            // метод acquire() запрашивает доступ к коду, следующему
        	// за вызовом метода, если доступ не разрешен, поток будет заблокирован в ожидании
        	// разрешения семафора
            semaphore.acquire();

            int parkingNumber = -1;

            //Ищем свободное место и паркуемся. 
            // synchronized блок необходим для синхронизации работы потоков,которым семафор дал разрешение.
            synchronized (parkingPlaces){
                for (int i = 0; i < 5; i++){
                    if (!parkingPlaces[i]) {      //Если место свободно
                        parkingPlaces[i] = true;  //занимаем его
                        parkingNumber = i;         //Наличие свободного места, гарантирует семафор
                        System.out.printf("Автомобиль №%d припарковался на месте %d.\n", carNumber, i);
                        break;
                    }
                }
            }

            Thread.sleep(5000);  // машина на парковке

            synchronized (parkingPlaces) {
            	parkingPlaces[parkingNumber] = false;//Освобождаем место
            }
            
            // освобождаем рсурс, возвращаем его семафору с помошью метода release()
            semaphore.release();
            System.out.printf("Автомобиль №%d покинул парковку.\n", carNumber);
        } catch (InterruptedException e) {
        }
    }
}