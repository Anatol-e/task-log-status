package com.open.status;

public class TaskExample {

    public static void main(String[] args) {
        SomeLogger logger = new SomeLogger();
        System.out.println("Action1");
        logger.log(20, "Hello");
        System.out.println("Action2");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Action3");
        System.out.println("Action4");
        logger.log(100, "Done!");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            logger.end();
        }
    }

}
