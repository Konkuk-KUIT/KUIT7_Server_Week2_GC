import java.util.ArrayList;
import java.util.List;

public class G1GCTest {

    // 장기 생존: 거래 내역 캐시
    static List<Transaction> transactionHistory = new ArrayList<>();
    // 캐시 교체: 최근 올라온 중고 매물
    static List<UsedItem> recentListings = new ArrayList<>();

    public static void main(String[] args) throws Exception {

        for (int i = 0; i < 2000; i++) {

            // 상황 1: 단기 생존 - 구매자가 매물 조회 후 이탈 (즉시 해제)
            Seller visitor = new Seller(i);
            UsedItem viewedItem = new UsedItem(i);

            // 상황 2: 장기 생존 - 거래 완료 -> 캐시에 거래 내역 보관
            if (i % 30 == 0) {
                transactionHistory.add(new Transaction(i, visitor, viewedItem));
            }

            // 상황 3: 버스트 - 인기 매물에 구매 요청 급증
            if (i % 500 == 0) {
                for (int j = 0; j < 2; j++) {
                    Seller burstSeller = new Seller(i * 100 + j);
                    UsedItem burstItem = new UsedItem(i * 100 + j);
                    transactionHistory.add(new Transaction(i * 100L + j, burstSeller, burstItem));
                }
            }

            // 상황 4: 캐시 교체 - 최근 매물 목록 갱신
            if (i % 150 == 0) {
                recentListings.clear();     // 기존 캐시 제거 -> GC target
                for (int j = 0; j < 5; j++) {
                    recentListings.add(new UsedItem(i + j));
                }
            }

            Thread.sleep(10);
        }
    }
}