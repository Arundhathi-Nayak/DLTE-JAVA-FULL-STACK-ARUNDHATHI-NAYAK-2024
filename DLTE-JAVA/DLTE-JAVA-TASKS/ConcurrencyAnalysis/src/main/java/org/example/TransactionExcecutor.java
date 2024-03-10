package org.example;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class TransactionExcecutor {
    public static void main(String[] args) {
        MultiThreadMain multiThreadMain=new MultiThreadMain();
        Executor executor = Executors.newCachedThreadPool();
        executor.execute(multiThreadMain);
        ThreadPoolExecutor poolExecutor= (ThreadPoolExecutor) executor;
        poolExecutor.shutdown();
    }
}
