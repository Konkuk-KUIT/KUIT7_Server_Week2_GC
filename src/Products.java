package src;

import java.util.ArrayList;
import java.util.List;

public class Products {
    private final List<Product> productList = new ArrayList<>();

    public void addProduct(Product product) {
        if(productList.size() >= 50) productList.remove(0);
        productList.add(product);
    }

    public int getSize() {
        return productList.size();
    }

    public Product removeProduct(int userMoney, String name) {
        for (Product product : productList) {
            Product result = product.compareProduct(userMoney, name);
            if(result != null) {
                productList.remove(product);
                return result;
            }
        }
        return null;
    }
}
