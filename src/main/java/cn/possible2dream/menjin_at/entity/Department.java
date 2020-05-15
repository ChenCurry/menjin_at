package cn.possible2dream.menjin_at.entity;

public class Department {
    private Integer scDepartmentid;

    private String scDepartmentname;

    private Integer scParentdepartmentid;

    private byte[] scTimestamp;

    public Integer getScDepartmentid() {
        return scDepartmentid;
    }

    public void setScDepartmentid(Integer scDepartmentid) {
        this.scDepartmentid = scDepartmentid;
    }

    public String getScDepartmentname() {
        return scDepartmentname;
    }

    public void setScDepartmentname(String scDepartmentname) {
        this.scDepartmentname = scDepartmentname == null ? null : scDepartmentname.trim();
    }

    public Integer getScParentdepartmentid() {
        return scParentdepartmentid;
    }

    public void setScParentdepartmentid(Integer scParentdepartmentid) {
        this.scParentdepartmentid = scParentdepartmentid;
    }

    public byte[] getScTimestamp() {
        return scTimestamp;
    }

    public void setScTimestamp(byte[] scTimestamp) {
        this.scTimestamp = scTimestamp;
    }
}