import domain.Song;

import java.util.ArrayList;
import java.util.List;

public class ParallelGCTest {
    static List<Song> PlayList = new ArrayList<>();

    public static void main(String[] args) throws InterruptedException {
        for(int i = 0; i < 1000; i++) {
            Song skip = new Song(i);

            if(i % 5 == 0) {
                skip.LoadFullData();
                PlayList.add(skip);
            }
            Thread.sleep(50);
        }
    }
}
