import java.util.*;


public class ParallelGCTest extends GCTest {
    static List<byte[]> longLived = new ArrayList<>();

    public ParallelGCTest() {

    }

    public static void main(String[] args) throws Exception {
        ParallelGCTest test = new ParallelGCTest();
        test.startSimulation();
        return;
    }
}
