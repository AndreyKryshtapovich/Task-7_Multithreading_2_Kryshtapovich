package by.epmtr.task7.reentrantlock_plus_condition;


/**
 * @author Andrey
 * Пример использования Reentrantlock и Condition. Необходимо решить задачу поставщика - потребителя,
 * синхронизировав доступ к хранилищу, таким образом, чтобы потребитель не мог ничего получить из пустого хранилища,
 * а поставщик не мог складировать товар в переполненное хранилище.
 *
 */
public class Start {

	public static void main(String[] args) {

		// Создаем объект хранилища, который будет являться разделяемым ресурсом
		Storage sharedObject = new Storage();

		// Создаем потребителя и поставщика
		Producer p = new Producer(sharedObject);
		Consumer c = new Consumer(sharedObject);

		// Запускаем потоки
		p.start();
		c.start();
	}

}

