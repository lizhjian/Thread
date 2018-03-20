package com.test05;

import sun.swing.BakedArrayList;

/**
 * 需求：
 * 有一个银行，有个储钱罐
 * 有两个储户，分别存300元
 * 每次存100 存3次
 *
 * 如何找问题
 * 1、明确那些代码是多线程运行代码
 * 2、明确共享数据
 * 3、明确多线程运行代码中哪些语句是操作共享数据的
 *
 */
class Bank{
    private int sum;
    Object object = new Object();
    public synchronized void add(int n){

      //  synchronized (object){
            sum = sum +n;
            try{
                Thread.sleep(20);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            System.out.println("sum----"+sum);
       // }


    }
}

class Cus implements Runnable{
    private Bank b = new Bank();
    @Override
    public void run() {
         for(int x=0;x<3;x++){
              b.add(100);
         }
    }
}


public class BankDemo {
    public static void main(String[] args) {
       Cus cus =new Cus();
       Thread t1 = new Thread(cus);
       Thread t2 = new Thread(cus);
       t1.start();
       t2.start();
    }
}
