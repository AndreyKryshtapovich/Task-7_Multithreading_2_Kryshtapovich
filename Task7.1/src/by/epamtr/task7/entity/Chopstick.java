package by.epamtr.task7.entity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epamtr.task7.helper.StringConstants;

public class Chopstick {

	private boolean used;
	private String name;
	private final static Logger rootLogger = LogManager.getRootLogger();

	public Chopstick(String name) {
		this.name = name;
	}
	// использованы synchronized методы для того, что бы только 1 поток-философ
	// мог единовременно заблокировать вилку
	
	public synchronized void take() {
		rootLogger.info(Thread.currentThread().getName() + StringConstants.used + name);
		this.used = true;
	}

	public synchronized void release() {
		rootLogger.info(Thread.currentThread().getName() + StringConstants.released + name);
		this.used = false;
	}
	public synchronized boolean isUsed(){
		return this.used;
	}
}
