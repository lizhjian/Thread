package com.test03;

/**
 * 买票程序
 */
class Ticket extends  Thread{
    private static int tick = 100;
    public void run(){
        while(true){
            if(tick>0){
                System.out.println(this.getName()+"***"+tick--);
            }

        }
    }
}


public class Main {
    public static void main(String[] args) {
        Ticket t1= new Ticket();
        Ticket t2= new Ticket();
        Ticket t3= new Ticket();
        Ticket t4= new Ticket();

        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
}
