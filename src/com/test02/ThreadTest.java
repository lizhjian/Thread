package com.test02;

class Test extends  Thread{
    private String threadName;

    public Test( String threadName) {
        this.threadName = threadName;
    }

    public void run(){
        for(int i=0;i<60;i++){
            System.out.println(this.getName()+"test---run"+i);
        }

    }
}

public class ThreadTest {
    public static void main(String[] args) {
        Test t1 = new Test("线程一");
        Test t2 = new Test("线程二");
        t1.start();
        t2.start();
        for(int x=0;x<60;x++){
            System.out.println("main---"+x);
        }
    }
}
