public class Person {
    String name;
    int age;
    byte[] payload; // 메모리 차지

    public Person(String name, int age, int size) {
        this.name = name;
        this.age = age;
        this.payload = new byte[size]; // 메모리 차지
    }
}
