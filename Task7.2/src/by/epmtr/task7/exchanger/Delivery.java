package by.epmtr.task7.exchanger;

import java.util.concurrent.Exchanger;
/**
 * 
 * @author Andrey
 * ������ ������������� Exchanger.
 * ���� ��� ���������: ���� ���� �� ������ A � ����� D, ������ �� ������ B � ����� �.
 * ������ AD � BC ������������ � ������ E. �� ������� A � B ����� ��������� ������� � ������ C � D.
 * ��� ����� ��������� � ������ E ������ ����������� � ���������� ���������������� ���������.
 */
public class Delivery {
    // ������ ������ Exchanger ��� ������ �������� ������ String
    private static final Exchanger<String> EXCHANGER = new Exchanger<>();

    public static void main(String[] args) throws InterruptedException {
        String[] p1 = new String[]{"{������� A->D}", "{������� A->C}"};//��������� ���� ��� 1-�� ���������
        String[] p2 = new String[]{"{������� B->C}", "{������� B->D}"};//��������� ���� ��� 2-�� ���������
        new Thread(new Truck(1, "A", "D", p1, EXCHANGER)).start();//���������� 1-� �������� �� � � D
        Thread.sleep(100);
        new Thread(new Truck(2, "B", "C", p2, EXCHANGER)).start();//���������� 2-� �������� �� � � �
    }
}