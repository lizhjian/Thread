package com.ProducerConsumerDemo;

/**
 * 当有多个生产者和消费者时要用while 和notifyAll
 */
class Resource{
    private String name;
    private int count = 1;
    private boolean flag = false;
    public synchronized void  set(String name){
        while(this.flag){
            try{
                wait();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        this.name=name +"---"+count++;
        System.out.println(Thread.currentThread().getName()+"..生产者."+this.name);
        flag = true;
        this.notifyAll();
    }
    public synchronized void out(){
        while (!flag){
            try{
              wait();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName()+"....消费者...."+this.name);
        flag = false;
        this.notifyAll();
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


public class ProducerConsumerDemo {
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
