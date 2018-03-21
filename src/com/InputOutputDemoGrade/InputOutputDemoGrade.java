package com.InputOutputDemoGrade;

/**
 * wait
 * notify
 * notifyAll
 * 都使用在同步中，因为要对持有监视器（锁）的线程操作
 * 所以要在使用的同步中，因为只有同步才有锁
 */
class Res{
    private String name;
    private String sex;
    private Boolean flag =false;

    public synchronized void set(String name,String sex) {
        if(flag){
            try{
                this.wait();      //当前线程必须拥有此对象监视器  ==》this是锁，等同于r.wait();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        this.name = name;
        this.sex = sex;
        flag =true;
        this.notify();
    }
    public synchronized void out(){
        if(!flag){
            try{
                this.wait();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        System.out.println(this.name+"-----"+this.sex);
        flag =false;
        this.notify(); //唤醒拿有当前锁的对象

    }

}

class Input implements Runnable{
    private  Res r;
    public Input(Res r) {
      this.r = r;
    }

    @Override
    public void run() {
       int x = 0;
       while (true){
            if(x==0){
                 r.set("小明","男");
            } else{
                r.set("小红","女");
            }
            x = (x+1)%2;

       }
    }
}

class Output implements Runnable{
    private Res r;

    public Output(Res r) {
        this.r = r;
    }

    @Override
    public void run() {
        while (true){
            r.out();
        }
    }
}

public class InputOutputDemoGrade {
    public static void main(String[] args) {

        //共享资源
        Res r = new Res();
        Input in = new Input(r);
        Output out = new Output(r);
        Thread t1 = new Thread(in);
        Thread t2 = new Thread(out);
        t1.start();
        t2.start();
    }
}
