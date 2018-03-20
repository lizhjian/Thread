package com.test07;
class Single{
    private static  Single s = null;

    private Single() {

    }
    /*public static synchronized   Single getInstance(){
        if(s==null){
            s= new Single();
        }
        return s;
    }*/
    //双重判断方式提高懒汉效率
    public static Single getInstance(){
        if (s==null){
            synchronized (Single.class){
                if(s==null){
                    /*---->A*/
                    s= new Single();
                }
            }
        }

        return s;
    }
}


public class SingleDemo  {

}
