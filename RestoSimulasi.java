import java.util.concurrent.atomic.AtomicInteger;

class Resto {
    private AtomicInteger chickenStock = new AtomicInteger(100);
    private AtomicInteger chickensold = new AtomicInteger(0);
    private AtomicInteger executions = new AtomicInteger(0);
    
    public void serveCustomer(String cashierName) {
        executions.incrementAndGet(); 

        try { Thread.sleep(10); } catch (InterruptedException e) {}
            if (chickenStock.get() > 0) {
                
                chickenStock.decrementAndGet();
                System.out.println(cashierName + " berhasil menjual 1 ayam. Sisa stok: " + chickenStock.get());
                chickensold.incrementAndGet();
            } else {
                System.out.println(cashierName + " gagal: Stok Habis!");
        }
    }

    public int getRemainingStock() {
        return chickenStock.get();
    }

    public int getChickenSold(){
        return chickensold.get();
    }

    public int getExecutions(){
        return executions.get();
    }
}

public class RestoSimulasi {
    public static void main(String[] args) throws InterruptedException {
        Resto ayamJuicyLuicyGallagher = new Resto();

        Runnable task = () -> {
            for (int i = 0; i < 40; i++) {
                ayamJuicyLuicyGallagher.serveCustomer(Thread.currentThread().getName());
            }
        };

        Thread kasir1 = new Thread(task, "Kasir-A");
        Thread kasir2 = new Thread(task, "Kasir-B");
        Thread kasir3 = new Thread(task, "Kasir-C");

        kasir1.start();
        kasir2.start();
        kasir3.start();

        kasir1.join();
        kasir2.join();
        kasir3.join();

        System.out.println("--- HASIL AKHIR STOK: " + ayamJuicyLuicyGallagher.getRemainingStock() + " ---");
        System.out.println("Chicken Sold: "+ ayamJuicyLuicyGallagher.getChickenSold());
        System.out.println(ayamJuicyLuicyGallagher.getExecutions());
    }
}