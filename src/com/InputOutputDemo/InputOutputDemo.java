package com.InputOutputDemo;
class Res{
    String name;
    String sex;
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
           synchronized (r){
               if(x==0){
                   r.name = "小明";
                   r.sex="男";
               }else{
                   r.name = "小红";
                   r.sex="女";
               }
           }
           x=(x+1)%2;
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
            synchronized (r){
                System.out.println(r.name+"-----"+r.sex);
            }

        }
    }
}

public class InputOutputDemo {
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
