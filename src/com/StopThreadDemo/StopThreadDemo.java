package com.StopThreadDemo;

/**
 * stop已过时。
 * 如何停止线程？
 * 只有一种，run方法结束
 * 开启多线程运行，云顶代码通常是循环结构
 * 只要控制住循环，就可以让run方法结束，也就是线程结束
 */
class StopThread implements Runnable{
    private  boolean flag = true;
    @Override
    public void run() {

        while(flag){
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
           t1.start();
           t2.start();
           int num = 0;
           while(true){
                if(num++==600){
                    st.changeFlag();
                     break;
                }
               System.out.println(Thread.currentThread().getName()+"......"+num);
        }
    }
}
