package cn.possible2dream.menjin_at.entity;

import java.util.Date;

public class OriginalRecord {
    //SC_AccessRecord 表
    private Long scSerierno;//记录号自增 百万级
    private Integer scDoorno;//设备号1-16
    private Long scCardguidno;//'%'+SC_CardGuidNO   卡号 10忆  不行的话换成Long
    private Byte scEventtypeid;//事件类型 0  要确定哪些事件是正常出来了的 要容错
    private Byte scInoutstatus;//进还是出
//    private  SC_DeviceRecNO;//该设备记录总次数
    private Date scRecordtime;//刷卡时间？
    private Date scAddtime;//记录时间？

    //SC_Employee 表
    private String scWorkerno;//工号
    private String scName;//姓名
    private String scMobileno;//完整卡号
    private Integer scIdtypeid;//类型
    private Integer scDepartmentid;//部门

    //SC_Department 表
    private String scDepartmentname;

    //private int messageType;//后台传给前台的消息类型

    //SC_Card 表
//    private  SC_Lever;
//    private  SC_Group;

    //SC_ESDCheckResult 表
//    private  SC_CheckResultID;//SC_EventTypeID

    //SC_EventType 表
//    private  SC_EventTypeID;//SC_EventTypeID

    //SC_Native 表 班别
//    private  SC_NativeID;

    //SC_OperateCardType 表 普通卡 管理卡
//    private  SC_OperateCardTypeID;


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

    public Long getScCardguidno() {
        return scCardguidno;
    }

    public void setScCardguidno(Long scCardguidno) {
        this.scCardguidno = scCardguidno;
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

    public Date getScAddtime() {
        return scAddtime;
    }

    public void setScAddtime(Date scAddtime) {
        this.scAddtime = scAddtime;
    }

    public String getScWorkerno() {
        return scWorkerno;
    }

    public void setScWorkerno(String scWorkerno) {
        this.scWorkerno = scWorkerno;
    }

    public String getScName() {
        return scName;
    }

    public void setScName(String scName) {
        this.scName = scName;
    }

    public String getScMobileno() {
        return scMobileno;
    }

    public void setScMobileno(String scMobileno) {
        this.scMobileno = scMobileno;
    }

    public Integer getScIdtypeid() {
        return scIdtypeid;
    }

    public void setScIdtypeid(Integer scIdtypeid) {
        this.scIdtypeid = scIdtypeid;
    }

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
        this.scDepartmentname = scDepartmentname;
    }

    @Override
    public String toString() {
        return "OriginalRecord{" +
                "scSerierno=" + scSerierno +
                ", scDoorno=" + scDoorno +
                ", scCardguidno=" + scCardguidno +
                ", scEventtypeid=" + scEventtypeid +
                ", scInoutstatus=" + scInoutstatus +
                ", scRecordtime=" + scRecordtime +
                ", scAddtime=" + scAddtime +
                ", scWorkerno='" + scWorkerno + '\'' +
                ", scName='" + scName + '\'' +
                ", scMobileno='" + scMobileno + '\'' +
                ", scIdtypeid=" + scIdtypeid +
                ", scDepartmentid=" + scDepartmentid +
                ", scDepartmentname='" + scDepartmentname + '\'' +
                '}';
    }
}