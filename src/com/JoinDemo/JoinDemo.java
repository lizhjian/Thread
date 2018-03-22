package com.JoinDemo;

/**
 * join
 * 当A线程执行到B线程的join方法时，A就会等待，等B线程都执行完，A才会执行
 * join可以用来临时加入线程执行
 *
 */
class  Demo implements Runnable{
    @Override
    public void run() {
        for (int x = 0;x<50;x++){
            System.out.println(Thread.currentThread().toString()+"......"+x);
            Thread.yield();//当前线程临时停一下 让出执行权
        }
    }
}


public class JoinDemo {
    public static void main(String[] args) throws InterruptedException {
        Demo demo = new Demo();
        Thread t1 = new Thread(demo);
        Thread t2 = new Thread(demo);
        t1.start();
        t1.setPriority(Thread.MAX_PRIORITY);
        t2.start();

       // t1.join();  //t1抢夺cpu执行权，main冻结停止，t1 t2 交替执行，等t1结束后main活过来

        for(int x =0;x<80;x++){
          //  System.out.println(Thread.currentThread().toString()+"...main...."+x);
        }
        System.out.println("over");
    }
}
