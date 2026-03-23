import data.Product;

import java.util.ArrayList;
import java.util.List;

public class ParallelGCTest {
    static List<Product> productArchive = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 500; i++) {
            String temporaryDescription = "A".repeat(512 * 1024);
            Product temporary = new Product(temporaryDescription + i);

            if (i % 10 == 0) {
                String permanentDescription = "A".repeat(512 * 1024);
                Product permanent = new Product(permanentDescription + i);
                productArchive.add(permanent);

                String longDescription = "A".repeat(1024 * 1024);
                Product heavy = new Product(longDescription + i);
                productArchive.add(heavy);
            }
            Thread.sleep(20);
        }
    }
}