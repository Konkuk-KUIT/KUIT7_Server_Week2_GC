public class User {
    private String name;
    private int age;
    private byte[] profileImage; // Large object

    public User(String name, int age) {
        this.name = name;
        this.age = age;
        // 1MB profile image
        this.profileImage = new byte[1024 * 1024];
    }
}
