package src;

import java.util.Arrays;
import java.util.Objects;

public class Product {
    private final String name;
    private final int productID;
    private final int price;
    private final byte[] imageData;

    public Product(String name, int productID, int price) {
        this.name = name;
        this.productID = productID;
        this.price = price;
        this.imageData = new byte[1024 * 1024 * 2];
    }

    public int getPrice() {
        return price;
    }

    public Product compareProduct(int userMoney, String name) {
        if(this.name.equals(name) && userMoney >= price) return this;
        return null;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Product product = (Product) object;
        return productID == product.productID && price == product.price && Objects.equals(name, product.name) && Objects.deepEquals(imageData, product.imageData);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, productID, price, Arrays.hashCode(imageData));
    }

    @Override
    public String toString() {
        return "상품 번호 : " + productID + ", 상품명 : " + name + ", 상품 가격 : " + price;
    }
}
