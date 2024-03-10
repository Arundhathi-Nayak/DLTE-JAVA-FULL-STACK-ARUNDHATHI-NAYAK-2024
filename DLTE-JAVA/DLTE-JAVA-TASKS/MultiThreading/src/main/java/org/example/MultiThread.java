package org.example;

public class MultiThread {
    public static void main(String[] args) {
        MultiThreadMain multiThread=new MultiThreadMain();
        Thread thread1 = new Thread(multiThread::displayTheAmount,"thread1");
        thread1.start();
        Thread thread2 = new Thread(multiThread::displayByBeneficiaryNames,"thread2");
        thread2.start();
        Thread thread3 = new Thread(multiThread::displayTheDetails,"thread2");
        thread3.start();
        Thread thread4= new Thread(multiThread,"thread4");
        Thread thread5= new Thread(multiThread,"thread5");
        Thread thread6= new Thread(multiThread,"thread6");
        Thread thread7= new Thread(multiThread,"thread7");
        Thread thread8= new Thread(multiThread,"thread8");
        Thread thread9= new Thread(multiThread,"thread9");
        Thread thread10= new Thread(multiThread,"thread10");
        thread4.start();
        thread5.start();
        thread6.start();
        thread7.start();
        thread8.start();
        thread9.start();
        thread10.start();

    }
}
