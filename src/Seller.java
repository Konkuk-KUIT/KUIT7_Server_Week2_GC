public class Seller {

    long id;             // 판매자 ID
    String nickname;     // 닉네임
    String location;     // 거래 희망 지역
    byte[] profileImage; // 프로필 이미지 (512KB)

    Seller(long id) {
        this.id = id;
        this.nickname = "판매자" + id;
        this.location = "서울시 광진구 " + id + "번지";
        this.profileImage = new byte[512 * 1024];    // 512KB
    }
}