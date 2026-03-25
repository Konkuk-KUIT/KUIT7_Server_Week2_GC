public class UsedItem {

    long id;        // 상품 ID
    String name;    // 상품 제목
    long price;      // 가격
    byte[] photo;   // 상품 사진 (1MB)

    UsedItem(long id) {
        this.id = id;
        this.name = "상품" + id;
        this.price = (int)(Math.random() * 10000) + 1000;
        this.photo = new byte[1024 * 1024]; // 1MB
    }
}