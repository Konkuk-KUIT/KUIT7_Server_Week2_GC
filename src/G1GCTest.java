import java.util.ArrayList;
import java.util.List;

/**
 * 쇼핑몰 사이트라고 가정
 * Static 키워드 사용 -> GC Root가 되어 장기 객체로 유도 가능해짐
 *
 * **/

public class G1GCTest {

    // static List로 계속 참조가 연결되어 장기 생존 가능
    static List<UserSession> activeSession = new ArrayList<>();

    public static void main(String[] args) throws InterruptedException {
        System.out.println("시뮬레이션 시작 !");

        for (int i = 0; i < 100000; i++) {

            //단기 객체 생성
            for(int j = 0; j < 100; j++) {
                new ProductSearch();
            }

            // 장기 객체 생성
            if (i % 10 == 0){
                UserSession userSession = new UserSession();
                activeSession.add(userSession);
            }

            // 거대 객체 생성
            if (i % 50 ==0){
                new ProductImage();
            }

            // 생성된 객체들을 삭제 -> GC 유도
            if (activeSession.size() > 1500){
                activeSession.clear();
            }

            Thread.sleep(1);

        }

        System.out.println("시뮬레이션 종료 !");


    }

    static class ProductSearch{private byte[] dummyData = new byte[1024];} // 1 KB
    static class UserSession{private byte[] sessionData = new byte[100 * 1024];} // 10 KB
    static class ProductImage{private byte[] imageData = new byte[1024 * 1024 * 2];} // 2 MB



}
