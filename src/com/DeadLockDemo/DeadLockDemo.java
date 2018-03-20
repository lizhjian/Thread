package com.DeadLockDemo;

/**
 * 死锁问题
 * 造成原因：
 * 同步中嵌套同步而锁却不同
 * 线程0 拿object 锁
 * 而线程1拿this锁
 */
class Ticket implements Runnable{
    private  int tick =100;
    Object object = new Object();
    Boolean flag = true;
    @Override
    public void run() {
        if(flag){
             while (true){
                 synchronized (object){
                     show();
                 }
             }
        }else{
            while (true)
                show();
        }
    }

    public synchronized void show(){
        synchronized (object){
            if(tick>0){
                try{
                    Thread.sleep(10);
                }catch (Exception e){
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+"...code"+tick--);
            }
        }
    }

}


public class DeadLockDemo {
    public static void main(String[] args) {
        Ticket t = new Ticket();
        Thread t1 = new Thread(t);
        Thread t2 = new Thread(t);
        t1.start();
        try{
            Thread.sleep(10);
        }catch (Exception e){
            e.printStackTrace();
        }
        t.flag =false;
        t2.start();
    }
}
