class SumTask implements Runnable {
    private int start;
    private int end;
    private long partialResult;
    private String threadName;

    public SumTask(int start, int end, String threadName) {
        this.start = start;
        this.end = end;
        this.threadName = threadName;
    }

    @Override
    public void run() {
        partialResult = 0;

        System.out.println(threadName + " menghitung dari " + start + " sampai " + end);

        for (int i = start; i <= end; i++) {
            partialResult += i;
        }

        System.out.println(threadName + " hasil parsial: " + partialResult);
    }

    public long getPartialResult() {
        return partialResult;
    }
}