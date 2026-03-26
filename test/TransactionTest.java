package test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import src.Manager;
import src.User;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransactionTest {
    private static final int PRODUCT_SIZE = 50;
    Manager manager;

    @BeforeEach
    public void setUp() {
        manager = new Manager();
    }

    @DisplayName("돈의 흐름이 제대로 이루어지는지 확인한다.")
    @Test
    public void transactionTest() {
        int resultUser = 4000;
        int resultManager = 6000;

        User user = new User("배정환", 1);
        user.addMoney(10000);
        for (int i = 0; i < PRODUCT_SIZE; i++) {
            manager.addProduct("상품" + i, 6000);
        }
        user.order(manager, "상품10");

        assertAll(
                () -> assertEquals(resultManager, manager.getMoney()),
                () -> assertEquals(resultUser, user.getMoney())
        );
    }

    @DisplayName("사용자가 돈이 없을 때 돈의 흐름이 제대로 이루어지는지 확인한다.")
    @Test
    public void transactionFailTest() {
        int resultUser = 5000;
        int resultManager = 0;

        User user = new User("배정환", 1);
        user.addMoney(5000);
        for (int i = 0; i < PRODUCT_SIZE; i++) {
            manager.addProduct("상품" + i, 6000);
        }
        user.order(manager, "상품10");

        assertAll(
                () -> assertEquals(resultManager, manager.getMoney()),
                () -> assertEquals(resultUser, user.getMoney())
        );
    }

    @DisplayName("상품이 제대로 고객에게 들어오는지 확인한다.")
    @Test
    public void productTest() {
        int resultUser = 1;
        int resultManager = 49;

        User user = new User("배정환", 1);
        user.addMoney(10000);
        for (int i = 0; i < PRODUCT_SIZE; i++) {
            manager.addProduct("상품" + i, 6000);
        }
        user.order(manager, "상품10");

        assertAll(
                () -> assertEquals(resultManager, manager.getSize()),
                () -> assertEquals(resultUser, user.getSize())
        );
    }

    @DisplayName("사용자가 돈이 없을 때 상품이 제대로 고객에게 들어오는지 확인한다.")
    @Test
    public void productFailTest() {
        int resultUser = 0;
        int resultManager = 50;

        User user = new User("배정환", 1);
        user.addMoney(5000);
        for (int i = 0; i < PRODUCT_SIZE; i++) {
            manager.addProduct("상품" + i, 6000);
        }
        user.order(manager, "상품10");

        assertAll(
                () -> assertEquals(resultManager, manager.getSize()),
                () -> assertEquals(resultUser, user.getSize())
        );
    }

    @DisplayName("로그가 제대로 출력되는지 확인한다.")
    @Test
    public void logTest() {
        String result = "Key - 사용자 번호 : 1, 사용자 이름 : 배정환 Value - 상품 번호 : 11, 상품명 : 상품10, 상품 가격 : 6000\n";

        User user = new User("배정환", 1);
        user.addMoney(10000);
        for (int i = 0; i < PRODUCT_SIZE; i++) {
            manager.addProduct("상품" + i, 6000);
        }
        user.order(manager, "상품10");

        assertEquals(result, manager.printLog());
    }
}
