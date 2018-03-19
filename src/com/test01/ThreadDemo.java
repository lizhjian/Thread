package com.test01;

/**
 * 第一种方式：继承Thread的方式
 * 1、定义类集成Thread
 * 2、复写Thread类中的run方法
 * 3、调用start方法
 *   ①启动线程
 *   ②调用run方法
 *
 *   为什么要覆盖run方法
 *   Thread类用于描述线程
 *   该类就定义了一个功能，用于存储线程要运行的代码，该存储功能就是run方法
 *
 */
class Demo extends Thread{
    public void run(){

        for (int x=0;x<60;x++){
            System.out.println("demo run----"+x);
        }
    }
}
public class ThreadDemo {
    public static void main(String[] args) {
        Demo d = new Demo();
        d.start();
        for (int x=0;x<60;x++){
            System.out.println("main run-----"+x);
        }
        /*Thread t = new Thread();
        t.start();*/
    }
}
