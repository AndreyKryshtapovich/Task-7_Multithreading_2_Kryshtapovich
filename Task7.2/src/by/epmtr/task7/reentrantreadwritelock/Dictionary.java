package by.epmtr.task7.reentrantreadwritelock;

import java.util.HashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
/**
 * 
 * @author Andrey
 *Пример использования ReentrantReadWriteLock. Необходимо синхронизировать доступ к словарю
 *так, чтобы несколько потоков могли одновременно читать данные, но если словарь обновляют (перезаписывают данные),
 *то чтение становится невозможным до окончания записи, затем чтение продолжается.
 */
public class Dictionary {
  
   private final ReentrantReadWriteLock readWriteLock =  new ReentrantReadWriteLock();
 
   private final Lock read  = readWriteLock.readLock(); // блокировка на чтение
   
   private final Lock write = readWriteLock.writeLock(); // блокировка на запись
   
   private HashMap<String, String> dictionary = new HashMap<String, String>(); // хранилище даных
   
   public void set(String key, String value) {
     write.lock();  // берем эксклюзивную блокировку на запись
     try {
      dictionary.put(key, value);
     } finally {
       write.unlock(); // снимаем эксклюзивную блокировку на запись
    }
   }
   
   public String get(String key) {
     read.lock(); // блокировка на чтение
     try{
       return dictionary.get(key);
     } finally {
       read.unlock(); // снимаем блокировку на чтение
     }
   }
 
   public String[] getKeys(){ // получаем все ключи из словаря
     read.lock();
     try{
       String keys[] = new String[dictionary.size()];
       return dictionary.keySet().toArray(keys);
     } finally {
       read.unlock();
     }
   }
   
   public static void main(String[] args) {
     Dictionary dictionary = new Dictionary();
     dictionary.set("java",  "object oriented");
     dictionary.set("linux", "rulez");
     Writer writer  = new Writer(dictionary, "Mr. Writer");
     Reader reader1 = new Reader(dictionary ,"Mrs Reader 1");
     Reader reader2 = new Reader(dictionary ,"Mrs Reader 2");

     // запустившись потоки будут параллельно читать одинаковые данные, при вызове перезаписи чтение остановится
     // после окончания записи потоки снова начнут параллельно читать уже измененные данные
     reader1.start(); 
     reader2.start();
     writer.start();
   }
 }