import java.util.ArrayList;
import java.util.List;

public class G1GCTest {
    static List<OrderReceipt> longTermStorage = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 10000; i++) {
            // Case 1: 단기 생존
            new User("[G1-USER-" + i + "]");

            // Case 2: 장기 생존
            if(i % 5 == 0) {
                longTermStorage.add(new OrderReceipt("[G1-ORDER-" + i + "]"));
            }

            // 객체 삭제
            if(longTermStorage.size() > 100) {
                longTermStorage.clear();
            }

            Thread.sleep(2);
        }
    }
}