package by.epmtr.task7.cyclicbarrier;

	// ������ ���� ����� ����������� ��� ������ �������� �������
    public  class FerryBoat implements Runnable {
        @Override
        public void run() {
            try {
                Thread.sleep(500);
                System.out.println("����� ���������� ����������!");
            } catch (InterruptedException e) {
            }
        }
    }