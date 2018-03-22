package com.StopThreadDemo;

/**
 * stop已过时。
 * 如何停止线程？
 * 只有一种，run方法结束
 * 开启多线程运行，运行代码通常是循环结构
 * 只要控制住循环，就可以让run方法结束，也就是线程结束
 *
 * 特殊情况
 * 当线程处于冻结状态
 * 就不会读取标记，那么线程就不会结束
 */
class StopThread implements Runnable{
    private  boolean flag = true;
    @Override
    public  synchronized void run() {
        System.out.println("&&&&&&&&&&&&&&&&");
        while(flag){
         /*  try{
               System.out.println("************");
             //  this.wait();
               System.out.println("===============");
           }catch (InterruptedException e){
               e.printStackTrace();
               System.out.println(Thread.currentThread().getName()+".....InterruptedException");
               //只要发生异常说明有人强制他结束
               flag = false;
           }*/

            System.out.println(Thread.currentThread().getName()+".....run");
        }
    }
    public void  changeFlag(){
        flag = false;
    }
}

public class StopThreadDemo {
    public static void main(String[] args) {
           StopThread st = new StopThread();
           Thread t1 = new Thread(st);
           Thread t2 = new Thread(st);
           t1.setDaemon(true);  //守护线程：当所有的前台线程后，守护线程自动结束
           t2.setDaemon(true);  //守护线程
           t1.start();
           t2.start();
           int num = 0;
           while(true){
                if(num++==60){
                  //  st.changeFlag();   //改变了状态但是没有终止线程
                  //  t1.interrupt();  //清除冻结状态  捕获异常后继续执行 又进入等待
                  //  t2.interrupt();
                     break;
                }
               System.out.println(Thread.currentThread().getName()+"......"+num);
        }
        System.out.println("over");
    }
}
