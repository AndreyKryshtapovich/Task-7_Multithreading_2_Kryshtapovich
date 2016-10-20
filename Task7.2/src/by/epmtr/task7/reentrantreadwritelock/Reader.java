package by.epmtr.task7.reentrantreadwritelock;

public class Reader extends Thread{
   
   private Dictionary dictionary = null;
   public Reader(Dictionary d, String threadName) {
     this.dictionary = d;
     this.setName(threadName);
   }
   
   @Override
   public void run() {
    for(int i = 0; i < 3; i++) {
       String [] keys = dictionary.getKeys();
       for (String key : keys) {
         String value = dictionary.get(key);   //чтение из словаря с использованием блокировки на чтение
         System.out.println(Thread.currentThread().getName()+" " + key + " : " + value);
       }

       // повторять чтение раз в секунду
     try {
       Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
 }

