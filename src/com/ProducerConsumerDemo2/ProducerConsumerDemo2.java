package com.ProducerConsumerDemo2;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * JDK1.5中提供了多线程设计解决方案
 * 将同步Synchronized替换成线程Lock操作
 * 将Object中的wait notify notifyAll 替换了Condition对象
 * 该对象可以通过Lock锁，进行获取
 * 该示例中，实现了本方只唤醒对象的操作
 */
class Resource{
    private String name;
    private int count = 1;
    private boolean flag = false;

    //定义锁  等同于sychronized(r)
    private Lock lock = new ReentrantLock();

    //具有wait notify功能
    private Condition condition_pro = lock.newCondition();
    private Condition condition_con = lock.newCondition();

    public  void  set(String name){

        lock.lock();
        try{
            while(this.flag){
                try{
                    // wait();
                    condition_pro.await();//设置当前线程等待（持有的锁是condition_pro）
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            this.name=name +"---"+count++;
            System.out.println(Thread.currentThread().getName()+"..生产者."+this.name);
            flag = true;
            condition_con.signal(); //
        }finally {
            lock.unlock();//无论如何都释放锁
        }


      //  this.notifyAll();
    }
    public  void out(){
        lock.lock();
        try{
            while(!flag){
                try{
                    condition_con.await();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName()+"....消费者...."+this.name);
            flag = false;
            condition_pro.signal();  //只唤醒condition_pro.await();<==> 唤醒对方
        }finally {
            lock.unlock();
        }

     //   this.notifyAll();
    }
}

class Producer implements  Runnable{
    private  Resource resource;

    public Producer(Resource resource) {
        this.resource = resource;
    }

    @Override
    public void run() {
        while(true){
            resource.set("+商品+");
        }
    }
}
class Consumer implements  Runnable{
    private  Resource resource;

    public Consumer(Resource resource) {
        this.resource = resource;
    }

    @Override
    public void run() {
        while(true){
            resource.out();
        }
    }
}


public class ProducerConsumerDemo2 {
    public static void main(String[] args) {
        //持有的公共变量
        Resource resource = new Resource();
        Producer producer = new Producer(resource);
        Consumer consumer = new Consumer(resource);
        Thread t1 = new Thread(producer);
        Thread t2 = new Thread(consumer);
        t1.start();
        t2.start();
        Thread t3 = new Thread(producer);
        Thread t4 = new Thread(consumer);
        t3.start();
        t4.start();
    }
}
