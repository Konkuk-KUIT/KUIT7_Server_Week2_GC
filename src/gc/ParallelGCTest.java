package gc;

import java.util.*;

public class ParallelGCTest {

    //GC에서 안죽고 Old로 감
    static List<User> longLived = new ArrayList<>();

    //15초 동안 실행
    public static void main(String[] args) throws Exception {

        long start = System.currentTimeMillis();//시간기록

        int i = 0;

        while (System.currentTimeMillis() - start < 15000) {

            //256KB 50개 생성
            for (int j = 0; j < 50; j++) {
                User temp = new User("temp", 256);
            }

            //Old 증가를 느리게 Parallel 사용
            if (i % 5 == 0) {
                longLived.add(new User("long", 1024));
            }

            //50개 한번에 삭제
            if (longLived.size() > 100) {
                longLived.subList(0, 50).clear();
            }

            Thread.sleep(50);//CPU 과부화 방지하고 GC 시간확보
            i++;
        }

        System.out.println("Finished Parallel GC Test");
    }
}
