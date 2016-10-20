package by.epmtr.task7.exchanger;

import java.util.concurrent.Exchanger;
/**
 * 
 * @author Andrey
 * Пример использования Exchanger.
 * Есть два грузовика: один едет из пункта A в пункт D, другой из пункта B в пункт С.
 * Дороги AD и BC пересекаются в пункте E. Из пунктов A и B нужно доставить посылки в пункты C и D.
 * Для этого грузовики в пункте E должны встретиться и обменяться соответствующими посылками.
 */
public class Delivery {
    // Объект класса Exchanger для обмена бъектами класса String
    private static final Exchanger<String> EXCHANGER = new Exchanger<>();

    public static void main(String[] args) throws InterruptedException {
        String[] p1 = new String[]{"{посылка A->D}", "{посылка A->C}"};//Формируем груз для 1-го грузовика
        String[] p2 = new String[]{"{посылка B->C}", "{посылка B->D}"};//Формируем груз для 2-го грузовика
        new Thread(new Truck(1, "A", "D", p1, EXCHANGER)).start();//Отправляем 1-й грузовик из А в D
        Thread.sleep(100);
        new Thread(new Truck(2, "B", "C", p2, EXCHANGER)).start();//Отправляем 2-й грузовик из В в С
    }
}