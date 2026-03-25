public class Transaction {

    long id;        // 거래 ID
    Seller seller;  // 판매자
    UsedItem item;  // 거래 상품
    int finalPrice; // 최종 거래가

    Transaction(long id, Seller seller, UsedItem item) {
        this.id = id;
        this.seller = seller;
        this.item = item;
        this.finalPrice = (int)(item.price * 0.9); // 10퍼센트 할인
    }
}