package gc;

import java.util.*;

public class G1GCTest {

    //GC에서 안죽고 살아있어 Old로 감
    static List<User> longLived = new ArrayList<>();

    public static void main(String[] args) throws Exception {

        long start = System.currentTimeMillis();//시작시간 기록

        int i = 0;

        //15초동안 실행
        while (System.currentTimeMillis() - start < 15000) {

            //256KB 객체 50개 생성, temp는 루프가 끝나면 사라짐
            for (int j = 0; j < 50; j++) {
                User temp = new User("temp", 256);
            }

            //1MB 객체 생성하고 리스트에 저장
            if (i % 3 == 0) {
                longLived.add(new User("long", 1024));
            }

            //너무 많이 쌓이면 Full GC 터져서 일부 객체 제거
            if (longLived.size() > 120) {
                longLived.remove(0);
            }

            Thread.sleep(50);//CPU 과부화 방지하고 GC 시간확보
            i++;
        }

        System.out.println("Finished G1 GC Test");
    }
}
