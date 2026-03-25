import java.util.ArrayList;
import java.util.List;

public class G1GCTest{

        static List<byte[]> smallStorage = new ArrayList<>();
        static List<byte[]> bigStorage = new ArrayList<>();

        public static void main(String[] args) throws Exception {

            for (int i = 1; i < 500; i++) {

                Product smallProduct = new Product(i, "small_" + i, i);
                String smallStatus = Checker.check(smallProduct);

                if (smallStatus.equals("SMALL")) {
                    smallStorage.add(new byte[512 * 1024]);
                } else {
                    bigStorage.add(new byte[1024 * 1024]);
                }


                if (i % 10 == 0) {
                    Product bigProduct = new Product(i, "big_" + i, 500 * i);
                    String bigStatus = Checker.check(bigProduct);

                    if (bigStatus.equals("BIG")) {
                        bigStorage.add(new byte[1024 * 1024]);
                    } else {
                        smallStorage.add(new byte[512 * 1024]);
                    }
                }

                Thread.sleep(50);
            }
        }
}