import java.util.ArrayList;
import java.util.List;

public class G1GCTest {
    static List<User> longLivedUsers = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 500; i++) {
            // 단기 생존 객체: 즉시 해제
            User shortLived = new User("ShortLived" + i, i);

            // 장기 생존 객체: Old 영역 유도
            if (i % 10 == 0) {
                longLivedUsers.add(new User("LongLived" + i, i));
            }
            Thread.sleep(50);
        }
    }
}
