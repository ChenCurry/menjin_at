package cn.possible2dream.menjin_at.entity;

public class EmployeeWithBLOBs extends Employee {
    private byte[] scPhoto;

    private byte[] scTimestamp;

    public byte[] getScPhoto() {
        return scPhoto;
    }

    public void setScPhoto(byte[] scPhoto) {
        this.scPhoto = scPhoto;
    }

    public byte[] getScTimestamp() {
        return scTimestamp;
    }

    public void setScTimestamp(byte[] scTimestamp) {
        this.scTimestamp = scTimestamp;
    }
}