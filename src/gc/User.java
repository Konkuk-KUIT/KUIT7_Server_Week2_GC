package gc;

class User {
    String name;
    byte[] payload; // 큰 객체

    public User(String name, int sizeInKB) {
        this.name = name;
        this.payload = new byte[sizeInKB * 1024];
    }
}
