package test;

import data.Product;

import java.util.ArrayList;
import java.util.List;

public class ParallelGCTest {
    static List<Product> productArchive = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        String shortDescription = "A".repeat(512 * 1024);
        String longDescription = "A".repeat(1024 * 1024);
        for (int i = 0; i < 2000; i++) {

            //단기 생존 객체
            Product temporary = new Product(shortDescription + i);
            //큰 단기 생존 객체
            Product temporaryHeavy = new Product(longDescription + i);
            if (i % 8 == 0) {
                //장기 생존 객체
                Product permanent = new Product(shortDescription + i);
                //큰 장기 생존 객체
                Product permanentHeavy = new Product(longDescription + i);

                productArchive.add(permanent);
                productArchive.add(permanentHeavy);
            }
            if (i % 400 == 0 && i != 0) {
                productArchive.clear();
            }
            Thread.sleep(5);
        }
    }
}