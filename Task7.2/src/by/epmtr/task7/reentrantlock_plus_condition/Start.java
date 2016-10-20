package by.epmtr.task7.reentrantlock_plus_condition;


/**
 * @author Andrey
 * ������ ������������� Reentrantlock � Condition. ���������� ������ ������ ���������� - �����������,
 * ��������������� ������ � ���������, ����� �������, ����� ����������� �� ��� ������ �������� �� ������� ���������,
 * � ��������� �� ��� ������������ ����� � ������������� ���������.
 *
 */
public class Start {

	public static void main(String[] args) {

		// ������� ������ ���������, ������� ����� �������� ����������� ��������
		Storage sharedObject = new Storage();

		// ������� ����������� � ����������
		Producer p = new Producer(sharedObject);
		Consumer c = new Consumer(sharedObject);

		// ��������� ������
		p.start();
		c.start();
	}

}

