package com.test04;

import static java.lang.Thread.sleep;

/**
 * 实现接口的方式
 * 1、定义类实现Runnable解扣子
 * 2、覆盖Runnable接口中run方法
 * 3、通过Thread类简历线程对象
 * 4、将Runnable接口的子类对象作为实际参数传递给Thread类的构造函数
 * 5、调用Thread类的start方法开启线程并调用Runnable接口子类的run方法
 *
 * 实现方式好处：避免了单线程的局限性
 * 在定义线程时，建议使用实现接口方式
 *
 * 通过分析发现  打印出0 等错票
 * 多线程运行出现了安全问题
 * 问题的原因：
 * 当多条语句在操作同一个多线程共享数据时，一个线程对多条语句只执行了一部分，
 * 还没有执行完，另一个线程参与进来执行
 * 对象
 *
 * 使用多线程的前提
 * 1、要有两个或者两个以上的线程
 * 2、必须是多个线程使用同一个锁
 *
 * 必须保证同步中只能有一个线程在执行
 * 好处：解决了多线程的安全问题
 * 弊端：多个线程需要判断锁，较为消耗资源
 */
class  Ticket  implements Runnable {
    private int tick = 100;
    Object object = new Object();
    @Override
    public  void run() {
        while (true){

            synchronized(object){
                if(tick>0){
                    try{
                        Thread.sleep(10);
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                    System.out.println(Thread.currentThread().getName()+"..sale---"+tick--);
                }
            }


        }
    }
}


public class TicketDemo {
    public static void main(String[] args) {
        Ticket t = new Ticket();
     Thread t1 = new Thread( t);
     Thread t2 = new Thread( t);

     t1.start();
     t2.start();

    }
}
