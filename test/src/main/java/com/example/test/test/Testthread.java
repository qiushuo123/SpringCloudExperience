package com.example.test.test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.FutureTask;

public class Testthread implements Callable{


//    public static void main(String[] args) {
//        int a=0;
//        outer:
//        for(int i=0;i<2;i++){
//            for(int j=0;j<2;j++){
//                if(j>i) continue outer;
//                a++;
//            }
//        }
//        System.out.println(a);
//    }
    @Override
    public String call() throws Exception {
        return "海贼王";
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask task=new FutureTask(new Testthread());
        new Thread(task).start();
        System.out.print(task.get());

    }
//
//    public static void main(String[] args) throws InterruptedException {
//        Thread thread1 = new Thread(() -> {
//            System.out.print("thread1");
//        });
//        Thread thread2 = new Thread(() -> {
//            System.out.print("thread2");
//        });
//        Thread thread3 = new Thread(() -> {
//            System.out.print("thread3");
//        });
//        thread1.start();
//        thread1.join();
//        thread2.start();
//        thread2.join();
//        thread3.start();
//        thread3.join();
//    }
}
