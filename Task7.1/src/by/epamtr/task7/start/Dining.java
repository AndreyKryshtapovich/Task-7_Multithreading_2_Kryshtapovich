package by.epamtr.task7.start;
import java.util.concurrent.Semaphore;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epamtr.task7.entity.Chopstick;
import by.epamtr.task7.entity.Philosopher;
import by.epamtr.task7.helper.StringConstants;
/**
 * @author Andrey
 * Задача "Проблема обедающих философов". Решение задачи базируется на том, что философы всегда пытаются взять 
 * левую вилку, только если получили к ней доступ пытаются взять правую вилку. Если правая вилка недоступна
 * философ кладет ее обратно, давая остальным философам возможность ее заблокировать. Объект Semaphore
 * играет роль "слуги", который не дает пятому философу даже попробовать взять левую вилку, если 4 остальных философа 
 * уже взяли по вилке.
 * 
 * Очередность в запросе вилок (сначала левая потом правая) введена для того, что бы исключить проблему "заговора соседей",
 * когда соседи философа поочередно забирают то левую, то правую вилку.
 * 
 * Введение объекта семафора ("слуги") исключает проблему взаимной блокировки (дедлока) философов или "голодной смерти"
 * 
 * 
 * 
 * Проблемы, аналогичные проблемам "обедающих философов" могут встретиться в таких предметных областях как 
 * доступ к накопителям информации, параллельные вычисления. Такие проблемы могут возникнуть например при проектировании 
 * системы управления заводом, производящим детали: многим рабочим нужно выполнить дневную норму, но количество станков ограничено
 * и меньше числа рабочих. Необходимо организовать работу так чтобы каждый рабочий смог выполнить необходимый объем работы.
 * Другим примером может быть организация работы светофоров на дорогах. Необходимо организовать работу так, чтобы светофоры
 * одновременно не разрешали движение всех автомобилей по всем направлениям, но и не блокировали все направления сразу.
 * Общим во всех этих предметных областях является то, что нужно правильно организовать доступ к ограниченному количеству ресурсов.
 */
public class Dining {
	// Создаем "справедливый" объект Semaphore, дающий возможность только 4 философам одновременно
	// пытаться получить доступ к вилкам
	private  final static Semaphore SEMAPHORE = new Semaphore(4, true);
	private final static Logger rootLogger = LogManager.getRootLogger();

	
	public static void main(String[] args) {
		// массив для хранения вилок
		Chopstick[] chopistics = new Chopstick[5];
		// создаем вилки
		for (int i = 0; i < chopistics.length; i++) {
			chopistics[i] = new Chopstick(StringConstants.c + i);
		}
		
		Philosopher[] philosophers = new Philosopher[5];
		// Создаем философов, у каждого философа есть доступ только к своей левой и правой вилке
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
		// для статистики выводим сколько раз каждый филосов поел
		for(int i = 0; i < philosophers.length; i++){
			rootLogger.info(StringConstants.p + i + StringConstants.ate+ philosophers[i].getCount() + StringConstants.spaghetti);
	}
	}
}

