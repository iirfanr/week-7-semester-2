import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.TimeUnit;
public class TryLockExample {
private static final Lock lock1 = new ReentrantLock();
private static final Lock lock2 = new ReentrantLock();
static class Task implements Runnable {
private String name;
public Task(String name) {
this.name = name;
}

@Override
    public void run() {
        while (true) {
            try {
            // coba ambil lock1
            if (lock1.tryLock(50, TimeUnit.MILLISECONDS)) {
                try {
                    System.out.println(name + " locked lock1");
                    // coba ambil lock2
                        if (lock2.tryLock(50, TimeUnit.MILLISECONDS)) {
                            try {
                                System.out.println(name + " locked lock2");
                                System.out.println(name + " doing work...");
                                break; // selesai, keluar loop
                            } finally {
                                lock2.unlock();
                            }
                            } else {
                                System.out.println(name + " could not lock lock2, retrying...");
                            }
                        } finally {
                            lock1.unlock();
                        }
                    } else {
                        System.out.println(name + " could not lock lock1, retrying...");
                    }
                // Tunggu sebentar sebelum mencoba lagi
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }       
}
public static void main(String[] args) {
    new Thread(new Task("Thread 1")).start();
    new Thread(new Task("Thread 2")).start();
    }
}
