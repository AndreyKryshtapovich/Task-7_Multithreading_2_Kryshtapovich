package by.epmtr.task7.reentrantreadwritelock;

public class Writer extends Thread{
   private Dictionary dictionary = null;
   public Writer(Dictionary d, String threadName) {
     this.dictionary = d;
     this.setName(threadName);
   }
   @Override
   public void run() {
	  try {
		Thread.sleep(1000); // ѕоток записи засывает, чтобы потоки чтени€ могли прчитать неизмененные данные 
	} catch (InterruptedException e1) {
		e1.printStackTrace();
	}
       String [] keys = dictionary.getKeys();
       for (String key : keys) {
         String newValue = getNewValueFromDatastore(key);
         dictionary.set(key, newValue); //обновление данных словар€ с использованием блокировки на запись. 
         								//Ќи один поток не считывает информацию в это врем€
      } 
   }

   public String getNewValueFromDatastore(String key){
	String s = new String(Thread.currentThread().getName() +" "+ "modified value");
	   return s;
   }
}
