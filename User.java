public class User {
    private final long id;
    private final String name;
    private final byte[] payload;

    public User(long id, String name, int sizeInKb) {
        this.id = id;
        this.name = name;
        this.payload = new byte[sizeInKb * 1024];
    }
}
