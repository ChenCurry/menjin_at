package cn.possible2dream.menjin_at.entity;

import java.util.Date;

public class AccessRecord {
    private Long scSerierno;

    private Integer scDoorno;

    private Long scUserdefinecardno;

    private Long scCardguidno;

    private Long scEmpid;

    private Byte scOpendoortypeid;

    private Byte scEventtypeid;

    private Byte scInoutstatus;

    private Date scRecordtime;

    private String scReserve;

    private Integer scDevicerecno;

    private Date scAddtime;

    private byte[] scTimestamp;

    public Long getScSerierno() {
        return scSerierno;
    }

    public void setScSerierno(Long scSerierno) {
        this.scSerierno = scSerierno;
    }

    public Integer getScDoorno() {
        return scDoorno;
    }

    public void setScDoorno(Integer scDoorno) {
        this.scDoorno = scDoorno;
    }

    public Long getScUserdefinecardno() {
        return scUserdefinecardno;
    }

    public void setScUserdefinecardno(Long scUserdefinecardno) {
        this.scUserdefinecardno = scUserdefinecardno;
    }

    public Long getScCardguidno() {
        return scCardguidno;
    }

    public void setScCardguidno(Long scCardguidno) {
        this.scCardguidno = scCardguidno;
    }

    public Long getScEmpid() {
        return scEmpid;
    }

    public void setScEmpid(Long scEmpid) {
        this.scEmpid = scEmpid;
    }

    public Byte getScOpendoortypeid() {
        return scOpendoortypeid;
    }

    public void setScOpendoortypeid(Byte scOpendoortypeid) {
        this.scOpendoortypeid = scOpendoortypeid;
    }

    public Byte getScEventtypeid() {
        return scEventtypeid;
    }

    public void setScEventtypeid(Byte scEventtypeid) {
        this.scEventtypeid = scEventtypeid;
    }

    public Byte getScInoutstatus() {
        return scInoutstatus;
    }

    public void setScInoutstatus(Byte scInoutstatus) {
        this.scInoutstatus = scInoutstatus;
    }

    public Date getScRecordtime() {
        return scRecordtime;
    }

    public void setScRecordtime(Date scRecordtime) {
        this.scRecordtime = scRecordtime;
    }

    public String getScReserve() {
        return scReserve;
    }

    public void setScReserve(String scReserve) {
        this.scReserve = scReserve == null ? null : scReserve.trim();
    }

    public Integer getScDevicerecno() {
        return scDevicerecno;
    }

    public void setScDevicerecno(Integer scDevicerecno) {
        this.scDevicerecno = scDevicerecno;
    }

    public Date getScAddtime() {
        return scAddtime;
    }

    public void setScAddtime(Date scAddtime) {
        this.scAddtime = scAddtime;
    }

    public byte[] getScTimestamp() {
        return scTimestamp;
    }

    public void setScTimestamp(byte[] scTimestamp) {
        this.scTimestamp = scTimestamp;
    }
}