package domain;

public class Song {

    private int songNumber;
    private byte[] songData;

    public Song(int songNumber) {
        this.songNumber = songNumber;
        this.songData = new byte[1024 * 512];
    }

    public void LoadFullData() {
        this.songData = new byte[1024 * 1024];
    }

}
