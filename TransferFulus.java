/*Instruksi Pengerjaan:
Analisis Masalah: Perhatikan source code di bawah ini,
masalah apa yang Anda temukan? Berikan penjelasannya pada 
setiap bagian source code dengan menyeluruh berdasarkan 
materi yang telah diperoleh pada kelas teori!
    Terjadi deadlock pada kedua thread.
    kedua thread dipanggil dan melakukan synchronized.
    thread pertama mengunci acc1 dan sleep selama 100ms,
    dan thread kedua mengunci acc2 dan sleep selama 100ms,
    thread pertama ingin menggunakan acc2 sedangkan thread kedua
    ingin menggunakan acc1,  karena keduanya terkunci, thread tidak
    dapat melanjutkannya dan terjadi deadlock.

Solusi Terhadap Masalah: Tuliskan solusi berupa source code 
dan penjelasan solusi terhadap masalah tersebut! Berikan 
penjelasannya pada setiap bagian source code!
    di kode ini, masalah bisa diselesaikan dengan memastika lock ordering pada thread melalui alur yang sama
    dengan synchronized (acc1) terus synchronized (acc2) pada kedua thread, dengan ini thread kedua akan
    langsung ke mengerjakan acc1 ketika thread 1 selesai dengan acc1, adn seterusnya pada acc2.

*/

class Account {
    int balance = 150;
}

public class TransferFulus {
    public static void main(String[] args) throws InterruptedException {
        Account acc1 = new Account();
        Account acc2 = new Account();

        // Thread 1: Menjumlahkan/ transfer fulus dari acc1 ke acc2
        Thread t1 = new Thread(() -> {
            synchronized (acc1) { // Berikan komentar apa yang dilakukan pada blok ini?
                //mengambil acc1 dan dilock
                System.out.println("Locking acc1 on t1...");
                try { Thread.sleep(100); } catch (Exception e) {} // Simulasi dengan memberikan jeda. Mengapa diperlukan Exception?
                        //karena thread.sleep dapat melempar InterruptedException yang merupakan checked exception, atau exception yang harus ditangani sebelum melakukan compile
                synchronized (acc2) { // Berikan komentar apa yang dilakukan pada blok ini?
                    //mengambil acc2 dan dilock serta melakukan kalkulasi
                    System.out.println("locking acc2, and tranferring balance from acc1 to acc2...");
                    acc2.balance += acc1.balance;
                }
            }
        });

        // Thread 2: Menjumlahkan/ transfer fulus dari acc2 ke acc1
        Thread t2 = new Thread(() -> {
            synchronized (acc1) { // Berikan komentar apa yang dilakukan pada blok ini?
                //mengambil acc1 dan dilock
                System.out.println("Locking acc1 on t2...");
                try { Thread.sleep(100); } catch (Exception e) {}

                synchronized (acc2) { // Berikan komentar apa yang dilakukan pada blok ini?
                    //mengambil acc2 dan dilock serta melakukan kalkulasi
                    System.out.println("locking acc2, and tranferring balance from acc2 to acc1...");
                    acc1.balance += acc2.balance;
                }
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("--- HASIL AKHIR ---");
        System.out.println("Saldo Akhir acc1: " + acc1.balance);
		System.out.println("Saldo Akhir acc2: " + acc2.balance);
    }
}

