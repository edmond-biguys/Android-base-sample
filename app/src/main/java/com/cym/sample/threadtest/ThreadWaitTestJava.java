package com.cym.sample.threadtest;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Calendar;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by caoj on 2024/1/22.
 */
public class ThreadWaitTestJava {
    private static final Object lock = new Object();
    ReentrantLock rLock = new ReentrantLock();
    public static void testJ01() {
        Thread t1 = new Thread(()->{
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            synchronized (lock) {
                //通知一个waiting的线程，如果有多个，任意选择其中一个wake up
                //lock.notify();
                //通知所有waiting的线程，wake up
                lock.notifyAll();
            }
        });
        System.out.println("t1 start");
        t1.start();
        Thread t2 = new Thread(()->{
            System.out.println("t2 start");
            try {
                synchronized (lock) {
                    lock.wait();
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("t2 stop");
        });
        Thread t3 = new Thread(()->{
            System.out.println("t3 start");
            try {
                synchronized (lock) {
                    lock.wait();
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("t3 stop");
        });
        t2.start();
        t3.start();
    }


    //验证悲观锁，
    //悲观锁：悲观锁认为一个线程去拿数据时一定会有其他线程对数据进行更改，所以每次读写数据时都会上锁。
    public static void testLock() {

        //t1、t2不管谁先执行，后执行的一个线程，都会等待先执行的线程，执行完毕，才开始执行read books
        Thread t1 = new Thread(()->{
            log("t1 start");
            synchronized (lock) {
                log("t1 lock");
                readBooks(11);
                log(" -- ");
                log(" -- t1 end lock");
            }
        });

        Thread t2 = new Thread(()->{
            log("t2 start");
            synchronized (lock) {
                log("t2 lock");
                readBooks(10);
                log(" -- ");
                log(" -- t2 end lock");
            }
        });
        t1.start();
        t2.start();
    }

    //验证lock wait()后，释放lock的情况。
    public static void testWaitReleaseLock() {
        Thread t1 = new Thread(()->{
            log("t1 start");
            synchronized (lock) {
                log("t1 lock");
                try {
                    log("t1 wait");
                    //这里调用lock.wait后，t1会释放lock的锁资源，t2就会开始执行
                    lock.wait(5000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                readBooks(11);
                log(" -- ");
                log(" -- t1 end lock");
            }
        });

        Thread t2 = new Thread(()->{
            log("t2 start");
            synchronized (lock) {
                log("t2 lock");
                readBooks(10);
                log(" -- ");
                log(" -- t2 end lock and notify");
                //t2执行完成后，调用notifyAll，通知t1的wait，继续执行后续程序
                lock.notifyAll();
            }
        });
        t1.start();
        t2.start();
    }

    //LockSupport.park阻塞当前thread，类似与Thread.sleep，两者都不会释放线程持有的锁资源
    //LockSupport.park可以被其他线程通过LockSupport.unpark(thread)方式唤醒，而sleep不行
    public static void testParkHoldLock() {
        Thread t1 = new Thread(()->{
            log("t1 start");
            synchronized (lock) {
                log("t1 lock start");
                //中断当前thread，但未释放锁资源
                LockSupport.park();
                readBooks(10, "=");
                log("--");
                log("t1 stop lock");
            }
        });


        Thread t2 = new Thread(()->{
            log("t2 start");
            synchronized (lock) {
                log("t2 lock start");
                readBooks(10);
                log("--");
                //通知停止阻塞
                LockSupport.unpark(t1);
                log("t2 stop and unPark");
            }
        });
        Thread t3 = new Thread(()->{
            log("t3 start");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            log("t3 unPark t1");
            LockSupport.unpark(t1);
        });
        t2.start();
        t1.start();
        t3.start();
    }



    private static void log(Object msg) {
        Calendar c = Calendar.getInstance();
        int y = c.get(Calendar.YEAR);
        int m = c.get(Calendar.MONTH) + 1;
        int d = c.get(Calendar.DAY_OF_MONTH);
        int h = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
        int s = c.get(Calendar.SECOND);
        int ms = c.get(Calendar.MILLISECOND);
        String time = y + "-" + m + "-" + d + " " + h + ":" + minute + ":" + s + "." + ms;
        System.out.println(time  + " ==> " + msg);
    }
    private static void readBooks(int times) {
        readBooks(times, "-");
    }
    private static void readBooks(int times, String content) {
        if (times > 200) {
            times = 200;
        }
        if (times < 1) {
            times = 1;
        }
        for (int i = 0; i < times; i++) {
            readABook(content);
        }
    }
    private static void readABook(String content) {
        String path = "H:\\git\\zlscreen-view-only\\app\\src\\main\\java\\com\\xzl\\screen\\module\\liftinfo\\deploy\\TestJava.java";
        try {
            FileReader reader = new FileReader(path);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line;
            while((line = bufferedReader.readLine()) != null) {
                System.out.print(content);
            }
            bufferedReader.close();
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
