public class DataChunk {
    private final String id;
    private final byte[] payload;
    private final String description;

    public DataChunk(String id, int sizeBytes) {
        this.id = id;
        this.payload = new byte[sizeBytes];
        this.description = "chunk-" + id + "-".repeat(100);
    }

    public String getId() { return id; }
}