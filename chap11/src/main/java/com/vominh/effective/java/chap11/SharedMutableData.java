package com.vominh.effective.java.chap11;

import java.util.concurrent.TimeUnit;

//synchronized keyword, which acts like a lock to a particular resource.
public class SharedMutableData {

    private static boolean stopRequested;


    /* Synchronized block */
    public static synchronized void requestStop() {
        stopRequested = true;
    }

    /* Synchronized block */
    public static synchronized boolean stopRequested() {
        return stopRequested;
    }

    public static void main(String[] args) throws InterruptedException {

        Thread backgroundThread = new Thread(() -> {
            int i = 0;
            while (!stopRequested)
                while (!stopRequested())
                    // System.out.println() is synchronized, removing the visibility issues with the original code.
                    i++;
        });
        backgroundThread.start();

        TimeUnit.SECONDS.sleep(1);
//        stopRequested = true;
        requestStop();

    }
}

