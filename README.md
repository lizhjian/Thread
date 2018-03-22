## 多线程
### 线程的状态
   * 被创建
   * 运行
   * 冻结（调用sleep或wait 放弃执行资格）
   * 消亡
   * 阻塞状态（具备运行资格，但没有执行权）
### 创建线程的两种方法
   * 继承Thread类 
     * 继承Thread类
     * 复写run方法
     * 调用线程start方法：创建并启动线程
   * 实现Runnable接口
     * 定义类实现Runnable接口
     * 覆盖Runnable接口中run方法
     * 通过Thread类简历线程对象
     * 将Runnable接口的子类对象作为实际参数传递给Thread类的构造函数
     * 调用Thread类的start方法开启线程并调用Runnable接口子类的run方法   
### 多线程安全问题
   #### 产生原因
   *  当多条语句在操作同一个多线程共享数据时，一个线程对多条语句只执行了一部分
       还有没执行完，另一个线程就过来参与执行
   ####  使用多线程前提  
   * 要有两个或者两个以上的线程
   * 必须是多个线程使用同一个锁
   #### 同步函数
   * 同步函数用的是this锁（this是当前对象,当前线程持有当前对象的锁，其他线程操作同一个对象（this）的锁，但没有执行权）
   * 如果函数被静态修饰后，使用的锁不是this,因为静态方法中也不可以定义this,静态进内存时，内存中没有本类对象，但是一定有该类对应的字节码文件对象===>类名.class
   * 静态同步方法使用的锁是该方法所在类的字节码文件对象  ====>类名.class 
   #### 懒汉设计模式(双重检查方式)
   ```
   //双重判断方式提高懒汉效率
      public static Single getInstance(){
          if (s==null){
          synchronized (Single.class){
            if(s==null){
                /*---->A*/   A不释放锁 B 无法进入synchronized
             s= new Single();
            }
           }
       }
       
         return s;
      }
   ```
   ####线程死锁
   * 场景1
   ```
   /**
    * 死锁问题
    * 造成原因：
    * 同步中嵌套同步而锁却不同
    * 线程0 拿object 锁
    * 而线程1拿this锁
    */
   class Ticket implements Runnable{
       private  int tick =100;
       Object object = new Object();
       Boolean flag = true;
       @Override
       public void run() {
           if(flag){
                while (true){
                    synchronized (object){
                    ----->A  1、A线程运行到这里B线程抢去CPU执行权
                             3、A线程继续执行，但是拿不到show函数的this锁，因为其被B持有
                             4、CPU切换到B线程，步骤5
                        show();
                    }
                }
           }else{
               while (true)
               ----->B   2、B线程运行到这里获取this锁并执行show方法，此时无法向下执行
                         因为object 被A持有,CPU切换到A
                         5、B线程持续等待object锁 切换到 3
                   show();
           }
       }
   
       public synchronized void show(){
           synchronized (object){
               if(tick>0){
                   try{
                       Thread.sleep(10);
                   }catch (Exception e){
                       e.printStackTrace();
                   }
                   System.out.println(Thread.currentThread().getName()+"...code"+tick--);
               }
           }
       }
   
   }
   

   ```
   * 场景2
   ```
   /**
    * 死锁虽然是两个线程对应了两个Test函数
    * 但是作为静态变量locka 或者 lockb 作为锁旗标 被另一个线程所占用，两个线程执行的不同的run 也不能拿到锁
    */
   class Test implements Runnable
   {
       private boolean flag;
   
       public Test(boolean flag) {
           this.flag = flag;
       }
   
       public Test() {
       }
   
       @Override
       public void run() {
           System.out.println(this);
          if(flag){
              synchronized (MyLock.locka){
              1、A线程持有MyLock.locka
              2、B线程抢去执行权
              6、A尝试获取MyLock.lockb锁，该锁被线程B持有，A没有执行权转向B线程（4）
                  System.out.println("if  locka");
                  synchronized (MyLock.lockb){
                      System.out.println("if  lockb");
                  }
              }
          }else{
              synchronized (MyLock.lockb){
              3、MyLock.lockb之前没有被任何一个线程持有，B进入方法后，自己持有
              4、继续执行等待MyLock.locka 
              5、没有执行权转向线程A（6）
                  System.out.println("else  lockb");
                  synchronized (MyLock.locka){
                      System.out.println("else  locka");
                  }
              }
          }
       }
   }
   
   class MyLock{
       static   Object locka = new Object();
       static   Object lockb = new Object();
   
   
   }
   
   //两个线程运行两个run方法
   public class DeadLockTest {
       public static void main(String[] args) {
         Test t = new Test();
         
         Thread t1 = new Thread(new Test(true));
         Thread t2 = new Thread(new Test(false));
         t1.start();
         t2.start();
       }
   }

   ```
   #### 多个生产者消费者
   