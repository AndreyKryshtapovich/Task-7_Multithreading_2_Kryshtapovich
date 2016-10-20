package by.epamtr.task7.start;
import java.util.concurrent.Semaphore;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epamtr.task7.entity.Chopstick;
import by.epamtr.task7.entity.Philosopher;
import by.epamtr.task7.helper.StringConstants;
/**
 * @author Andrey
 * ������ "�������� ��������� ���������". ������� ������ ���������� �� ���, ��� �������� ������ �������� ����� 
 * ����� �����, ������ ���� �������� � ��� ������ �������� ����� ������ �����. ���� ������ ����� ����������
 * ������� ������ �� �������, ����� ��������� ��������� ����������� �� �������������. ������ Semaphore
 * ������ ���� "�����", ������� �� ���� ������ �������� ���� ����������� ����� ����� �����, ���� 4 ��������� �������� 
 * ��� ����� �� �����.
 * 
 * ����������� � ������� ����� (������� ����� ����� ������) ������� ��� ����, ��� �� ��������� �������� "�������� �������",
 * ����� ������ �������� ���������� �������� �� �����, �� ������ �����.
 * 
 * �������� ������� �������� ("�����") ��������� �������� �������� ���������� (�������) ��������� ��� "�������� ������"
 * 
 * 
 * 
 * ��������, ����������� ��������� "��������� ���������" ����� ����������� � ����� ���������� �������� ��� 
 * ������ � ����������� ����������, ������������ ����������. ����� �������� ����� ���������� �������� ��� �������������� 
 * ������� ���������� �������, ������������ ������: ������ ������� ����� ��������� ������� �����, �� ���������� ������� ����������
 * � ������ ����� �������. ���������� ������������ ������ ��� ����� ������ ������� ���� ��������� ����������� ����� ������.
 * ������ �������� ����� ���� ����������� ������ ���������� �� �������. ���������� ������������ ������ ���, ����� ���������
 * ������������ �� ��������� �������� ���� ����������� �� ���� ������������, �� � �� ����������� ��� ����������� �����.
 * ����� �� ���� ���� ���������� �������� �������� ��, ��� ����� ��������� ������������ ������ � ������������� ���������� ��������.
 */
public class Dining {
	// ������� "������������" ������ Semaphore, ������ ����������� ������ 4 ��������� ������������
	// �������� �������� ������ � ������
	private  final static Semaphore SEMAPHORE = new Semaphore(4, true);
	private final static Logger rootLogger = LogManager.getRootLogger();

	
	public static void main(String[] args) {
		// ������ ��� �������� �����
		Chopstick[] chopistics = new Chopstick[5];
		// ������� �����
		for (int i = 0; i < chopistics.length; i++) {
			chopistics[i] = new Chopstick(StringConstants.c + i);
		}
		
		Philosopher[] philosophers = new Philosopher[5];
		// ������� ���������, � ������� �������� ���� ������ ������ � ����� ����� � ������ �����
		philosophers[0] = new Philosopher(chopistics[0], chopistics[1],SEMAPHORE);
		philosophers[1] = new Philosopher(chopistics[1], chopistics[2],SEMAPHORE);
		philosophers[2] = new Philosopher(chopistics[2], chopistics[3],SEMAPHORE);
		philosophers[3] = new Philosopher(chopistics[3], chopistics[4],SEMAPHORE);
		philosophers[4] = new Philosopher(chopistics[4], chopistics[0],SEMAPHORE);

		for (int i = 0; i < philosophers.length; i++) {
			philosophers[i].setName(StringConstants.p + i);
			philosophers[i].start();
		}
		
		for(int i = 0; i < philosophers.length; i++){
				try {
					philosophers[i].join();
				} catch (InterruptedException e) {
					rootLogger.error(e.getMessage());
				}
		}
		// ��� ���������� ������� ������� ��� ������ ������� ����
		for(int i = 0; i < philosophers.length; i++){
			rootLogger.info(StringConstants.p + i + StringConstants.ate+ philosophers[i].getCount() + StringConstants.spaghetti);
	}
	}
}

