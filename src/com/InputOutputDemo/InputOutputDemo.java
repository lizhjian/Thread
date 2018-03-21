package com.InputOutputDemo;

/**
 * wait
 * notify
 * notifyAll
 * 都使用在同步中，因为要对持有监视器（锁）的线程操作
 * 所以要在使用的同步中，因为只有同步才有锁
 */
class Res{
    String name;
    String sex;
    Boolean flag =false;
}
class Input implements Runnable{
    private Res r;
    public Input(Res r) {
      this.r = r;
    }

    @Override
    public void run() {
       int x = 0;
       while (true){
          synchronized (r){
              if(r.flag)
                  try {
                    r.wait();            //当前线程必须拥有此对象监视器
                  }catch (Exception e){
                  e.printStackTrace();
                  }
                  if(x==0){
                   r.name = "小明";
                   r.sex="男";
                  }else{
                      r.name = "小红";
                      r.sex="女";
                  }
                  x=(x+1)%2;
                  r.flag = true;
                  r.notify();
          }

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
                if(!r.flag){   //没有资源
                     try{
                         r.wait();
                     }catch (Exception e){
                         e.printStackTrace();
                     }

                }
                System.out.println(r.name+"---------"+r.sex);
                r.flag = false;
                r.notify();
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
