package test;

import obj.Order;

import java.util.ArrayList;
import java.util.List;



public class ParallelGCTest {

    static List<Order> longLived = new ArrayList<>();

    public static void main(String[] args) throws Exception {

        long start = System.currentTimeMillis();

        while (System.currentTimeMillis() - start < 20000) { // 20초

            for (int i = 0; i < 200; i++) {

                new Order();

                Order temp = new Order();

                if (i % 15 == 0) {
                    longLived.add(new Order());

                    if (longLived.size() > 30) {
                        longLived.remove(0);
                    }
                }
            }

            Thread.sleep(50);
        }

        System.out.println("Done");
    }
}