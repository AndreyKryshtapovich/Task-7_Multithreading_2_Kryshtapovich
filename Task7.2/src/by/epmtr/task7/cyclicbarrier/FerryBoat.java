package by.epmtr.task7.cyclicbarrier;

	// ƒанный таск будет выполн€тьс€ при каждом открытии барьера
    public  class FerryBoat implements Runnable {
        @Override
        public void run() {
            try {
                Thread.sleep(500);
                System.out.println("ѕаром переправил автомобили!");
            } catch (InterruptedException e) {
            }
        }
    }