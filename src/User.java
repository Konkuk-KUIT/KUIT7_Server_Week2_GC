import java.util.UUID;

public class User {
    private String userId;
    private String name;

    private byte[] data;

    public User(String name, boolean isLarge) {
        this.userId = UUID.randomUUID().toString();
        this.name = name;

        if (isLarge) {
            this.data = new byte[1100 * 1024];
        } else {
            this.data = new byte[1024];
        }
    }

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println("User 객체 수거됨: " + userId);
    }
}