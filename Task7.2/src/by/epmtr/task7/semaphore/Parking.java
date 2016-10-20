package by.epmtr.task7.semaphore;

import java.util.concurrent.Semaphore;
/**
 * 
 * @author Andrey
 *Пример использования Semaphore. Существует парковка, которая одновременно может вмещать не более 5 автомобилей. 
 *Если парковка заполнена полностью, то вновь прибывший автомобиль должен подождать пока не освободится хотя бы одно место.
 * После этого он сможет припарковаться.
 */
public class Parking {
	private  final static boolean[] PARKING_PLACES = new boolean[5]; //true - место занято, false - свободно
	// Второй параметр конструктора задает "справедливый" семафор.
	// метод acquire() будет давать разрешения в порядке очереди
	private  final static Semaphore SEMAPHORE = new Semaphore(5, true);
	public static void main(String[] args) throws InterruptedException {
        for (int i = 1; i <= 7; i++) {
           new Thread(new Car(i,SEMAPHORE,PARKING_PLACES)).start();
            Thread.sleep(400);
        }
    }
	
	
}
