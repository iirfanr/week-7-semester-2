import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ParallelSum {
    public static void main(String[] args) throws InterruptedException {
        Scanner input = new Scanner(System.in);

        System.out.print("Masukkan jumlah thread: ");
        int numThreads = input.nextInt();

        System.out.print("Masukkan angka akhir: ");
        int maxNumber = input.nextInt();

        List<Thread> threads = new ArrayList<>();
        List<SumTask> tasks = new ArrayList<>();

        int range = maxNumber / numThreads;
        int start = 1;

        // divide
        for (int i = 0; i < numThreads; i++) {
            int end = (i == numThreads - 1) ? maxNumber : start + range - 1;
            //if i adalah thread terakhir, maka end = maxNumber, else end = start +range - 1

            SumTask task = new SumTask(start, end, "Thread-" + (i + 1));
            Thread t = new Thread(task);

            tasks.add(task);
            threads.add(t);

            start = end + 1;
        }

        // start threads
        for (Thread t : threads) {
            t.start();
        }

        // synchronize threads
        for (Thread t : threads) {
            t.join();
        }

        // Gabungkan hasil parsial
        long total = 0;
        for (SumTask task : tasks) {
            total += task.getPartialResult();
        }

        System.out.println("\n=== HASIL AKHIR ===");
        System.out.println("Total penjumlahan: " + total);

        input.close();
    }
}
