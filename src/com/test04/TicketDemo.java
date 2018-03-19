package com.test04;

/**
 * 实现接口的方式
 * 1、定义类实现Runnable解扣子
 * 2、覆盖Runnable接口中run方法
 * 3、通过Thread类简历线程对象
 * 4、将Runnable接口的子类对象作为实际参数传递给Thread类的构造函数
 * 5、调用Thread类的start方法开启线程并调用Runnable接口子类的run方法
 *
 */
class  Ticket implements Runnable{
    private int tick = 100;
    @Override
    public void run() {
        while (true){
            if(tick>0){
                System.out.println(Thread.currentThread().getName()+"..sale"+tick--);
            }
        }
    }
}


public class TicketDemo {
    public static void main(String[] args) {
        Ticket t = new Ticket();
     Thread t1 = new Thread( t);
     Thread t2 = new Thread( t);
     Thread t3 = new Thread( t);
     Thread t4 = new Thread( t);
     t1.start();
     t2.start();
     t3.start();
     t4.start();
    }
}
