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
		Thread.sleep(1000); // ����� ������ ��������, ����� ������ ������ ����� �������� ������������ ������ 
	} catch (InterruptedException e1) {
		e1.printStackTrace();
	}
       String [] keys = dictionary.getKeys();
       for (String key : keys) {
         String newValue = getNewValueFromDatastore(key);
         dictionary.set(key, newValue); //���������� ������ ������� � �������������� ���������� �� ������. 
         								//�� ���� ����� �� ��������� ���������� � ��� �����
      } 
   }

   public String getNewValueFromDatastore(String key){
	String s = new String(Thread.currentThread().getName() +" "+ "modified value");
	   return s;
   }
}
