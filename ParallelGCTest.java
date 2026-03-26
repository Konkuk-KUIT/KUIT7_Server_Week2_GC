import java.util.ArrayList;
import java.util.List;

public class ParallelGCTest {
    static List<User> longLived = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        long start = System.currentTimeMillis();
        long id = 0;

        while (System.currentTimeMillis() - start < 15_000) {
            for (int i = 0; i < 100; i++) {
                User shortLived = new User(id++, "short-" + i, 256); // 단기 생존 객체

                if (i % 10 == 0) {
                    longLived.add(new User(id++, "long-" + i, 1024)); // 장기 생존 객체
                }

                if (i % 25 == 0) {
                    User large = new User(id++, "large-" + i, 2048); // 큰 객체
                }
            }

            if (longLived.size() > 60) {
                longLived.subList(0, 10).clear();
            }

            Thread.sleep(100);
        }
    }
}