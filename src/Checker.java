public class Checker {
    public static String check(Product product) {
        if (product.size > 500) {
            return "BIG";
        } else {
            return "SMALL";
        }
    }
}