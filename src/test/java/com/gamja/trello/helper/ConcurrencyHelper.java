package com.gamja.trello.helper;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ConcurrencyHelper {

    private static final int THREAD = 10;
    private static final int THREAD_POOL = 20;
//    private static final int DELAY_MILLIS = 10;
    private static final int DELAY_MILLIS = 0;


    public static int execute(final MyExecutable executable, int id)
            throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(THREAD);
        CountDownLatch latch = new CountDownLatch(THREAD_POOL);

        int size = 100000;

        for (int i = 1; i <= THREAD; i++) {
            int finalI = i;
            executorService.submit(() -> {
                try {
                    executable.execute(id + (finalI * size));
                } catch (final Throwable e) {
                    System.out.println(e.getClass().getName());
                } finally {
                    System.out.println(Thread.currentThread().getName() + "완료");
                    latch.countDown();
                }
            });
        }

        latch.await();

        return THREAD_POOL;
    }

    public static int getTHREAD() {
        return THREAD;
    }
}