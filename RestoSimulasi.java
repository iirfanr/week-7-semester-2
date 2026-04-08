/*Instruksi Pengerjaan:
Analisis Masalah: Perhatikan source code di bawah ini, masalah apa yang Anda temukan? 
Berikan penjelasannya pada setiap bagian source code dengan menyeluruh berdasarkan 
materi yang telah diperoleh pada kelas teori!

Solusi Terhadap Masalah: Tuliskan solusi berupa source code dan penjelasan 
solusi terhadap masalah tersebut! Berikan penjelasannya pada 
setiap bagian source code!
*/

class Resto {
    private int chickenStock = 100;
    private int chickensold = 0;
    private int executions = 0;

    public void serveCustomer(String cashierName) {
        executions += 1;
            if (chickenStock > 0) {
                try { Thread.sleep(10); } catch (InterruptedException e) {}
                
                chickenStock--; 
                System.out.println(cashierName + " berhasil menjual 1 ayam. Sisa stok: " + chickenStock);
                chickensold += 1;
            } else {
                System.out.println(cashierName + " gagal: Stok Habis!");
        }
    }

    public int getRemainingStock() {
        return chickenStock;
    }

    public int getChickenSold(){
        return chickensold;
    }

    public int getExecutions(){
        return executions;
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