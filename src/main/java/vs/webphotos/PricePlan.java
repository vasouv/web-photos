package vs.webphotos;

public enum PricePlan {

    FREE("Free", 2, false),
    PERSONAL("Personal", 5, false),
    VIDEO("Video", 10, true);

    private String name;
    private int capacityMB;
    private boolean uploadVideo;

    PricePlan(String name, int capacityMB, boolean uploadVideo) {
        this.name = name;
        this.capacityMB = capacityMB;
        this.uploadVideo = uploadVideo;
    }

    public String getName() {
        return name;
    }

    public int getCapacityMB() {
        return capacityMB;
    }

    public boolean canUploadVideo() {
        return uploadVideo;
    }

    public Long getCapacityBytes() {
        return capacityMB * 1_000_000L;
    }

    public boolean hasAvailableCapacity(Long usedCapacity) {
        return getCapacityBytes() - usedCapacity > 0;
    }
}
