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

     