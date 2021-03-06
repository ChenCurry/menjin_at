package cn.possible2dream.menjin_at.entity;

import java.util.Arrays;
import java.util.Date;

public class AccessRecord {
    private Long scSerierno;

    private Integer scDoorno;

    private Long scUserdefinecardno;

    private Long scCardguidno;

    private Long scEmpid;

    private Short scOpendoortypeid;

    private Short scEventtypeid;

    private Short scInoutstatus;

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

    public Short getScOpendoortypeid() {
        return scOpendoortypeid;
    }

    public void setScOpendoortypeid(Short scOpendoortypeid) {
        this.scOpendoortypeid = scOpendoortypeid;
    }

    public Short getScEventtypeid() {
        return scEventtypeid;
    }

    public void setScEventtypeid(Short scEventtypeid) {
        this.scEventtypeid = scEventtypeid;
    }

    public Short getScInoutstatus() {
        return scInoutstatus;
    }

    public void setScInoutstatus(Short scInoutstatus) {
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
        this.scReserve = scReserve;
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

    @Override
    public String toString() {
        return "AccessRecord{" +
                "scSerierno=" + scSerierno +
                ", scDoorno=" + scDoorno +
                ", scUserdefinecardno=" + scUserdefinecardno +
                ", scCardguidno=" + scCardguidno +
                ", scEmpid=" + scEmpid +
                ", scOpendoortypeid=" + scOpendoortypeid +
                ", scEventtypeid=" + scEventtypeid +
                ", scInoutstatus=" + scInoutstatus +
                ", scRecordtime=" + scRecordtime +
                ", scReserve='" + scReserve + '\'' +
                ", scDevicerecno=" + scDevicerecno +
                ", scAddtime=" + scAddtime +
                ", scTimestamp=" + Arrays.toString(scTimestamp) +
                '}';
    }
}