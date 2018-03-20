package com.DeadLockTest;

/**
 * 死锁虽然是两个线程对应了两个Test函数
 * 但是作为静态变量locka 或者 lockb 作为锁旗标 被另一个线程所占用，两个线程执行的不同的run 也不能拿到锁
 */
class Test implements Runnable
{
    private boolean flag;

    public Test(boolean flag) {
        this.flag = flag;
    }

    public Test() {
    }

    @Override
    public void run() {
        System.out.println(this);
       if(flag){
           synchronized (MyLock.locka){
               System.out.println("if  locka");
               synchronized (MyLock.lockb){
                   System.out.println("if  lockb");
               }
           }
       }else{
           synchronized (MyLock.lockb){
               System.out.println("else  lockb");
               synchronized (MyLock.locka){
                   System.out.println("else  locka");
               }
           }
       }
    }
}

class MyLock{
    static   Object locka = new Object();
    static   Object lockb = new Object();


}


public class DeadLockTest {
    public static void main(String[] args) {
      Test t = new Test();

      Thread t1 = new Thread(new Test(true));
      Thread t2 = new Thread(new Test(false));
      t1.start();
      t2.start();
    }
}
