package src;

public class G1GCTest {
    private static final int USER_SIZE = 10;
    private static final int PRODUCT_SIZE = 50;

    public static void main(String[] args) {
        System.out.println("========== G1GC Test Start ==========");

        Manager manager = new Manager();
        Users users = new Users();

        for (int i = 0; i < PRODUCT_SIZE; i++) {
            manager.addProduct("상품" + i, 6000);
        }

        for (int i = 0; i < USER_SIZE; i++) {
            User user = new User("사용자" + i, i);
            user.addMoney(1000000); // 돈을 충분히 충전
            users.addUser(user);
        }

        long startTime = System.currentTimeMillis();
        long duration = 15000;
        int count = 0;

        while (System.currentTimeMillis() - startTime < duration) {
            User currentUser = users.getUser(count % USER_SIZE);
            String targetItem = "타겟팅 아이템" + (count % PRODUCT_SIZE);

            currentUser.order(manager, targetItem);

            // 100번의 주문마다 새로운 대형 상품 추가
            if (count % 100 == 0) {
                currentUser.addMoney(1000000);
                manager.addProduct("새로운 상품" + count, 10000);
            }

            count++; // 주문 횟수 1 증가
        }

        System.out.println("========== GC Test End ==========");
        System.out.println("Total Transactions: " + count);
    }
}
