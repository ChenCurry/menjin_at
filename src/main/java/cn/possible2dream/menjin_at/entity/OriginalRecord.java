package cn.possible2dream.menjin_at.entity;

import cn.hutool.core.date.DateTime;

public class OriginalRecord {
    //SC_AccessRecord 表

    private Integer SC_SerierNO;//记录号自增 百万级
    private Integer SC_DoorNO;//设备号1-16
    private Integer SC_CardGuidNO;//'%'+SC_CardGuidNO   卡号 10忆  不行的话换成Long
    private Integer SC_EventTypeID;//事件类型 0  要确定哪些事件是正常出来了的 要容错
    private Integer SC_InOutStatus;//进还是出
//    private Integer SC_DeviceRecNO;//该设备记录总次数
    private DateTime SC_RecordTime;//刷卡时间？
    private DateTime SC_AddTime;//记录时间？

    //SC_Employee 表
    private String SC_WorkerNO;//工号
    private String SC_Name;//姓名
    private String SC_MobileNO;//完整卡号
    private Integer SC_IDTypeID;//类型
    private Integer SC_DepartmentID;//部门

    //SC_Department 表
    private String SC_DepartmentName;

    //SC_Card 表
//    private Integer SC_Lever;
//    private Integer SC_Group;

    //SC_ESDCheckResult 表
//    private Integer SC_CheckResultID;//SC_EventTypeID

    //SC_EventType 表
//    private Integer SC_EventTypeID;//SC_EventTypeID

    //SC_Native 表 班别
//    private Integer SC_NativeID;

    //SC_OperateCardType 表 普通卡 管理卡
//    private Integer SC_OperateCardTypeID;


    public Integer getSC_SerierNO() {
        return SC_SerierNO;
    }

    public void setSC_SerierNO(Integer SC_SerierNO) {
        this.SC_SerierNO = SC_SerierNO;
    }

    public Integer getSC_DoorNO() {
        return SC_DoorNO;
    }

    public void setSC_DoorNO(Integer SC_DoorNO) {
        this.SC_DoorNO = SC_DoorNO;
    }

    public Integer getSC_CardGuidNO() {
        return SC_CardGuidNO;
    }

    public void setSC_CardGuidNO(Integer SC_CardGuidNO) {
        this.SC_CardGuidNO = SC_CardGuidNO;
    }

    public Integer getSC_EventTypeID() {
        return SC_EventTypeID;
    }

    public void setSC_EventTypeID(Integer SC_EventTypeID) {
        this.SC_EventTypeID = SC_EventTypeID;
    }

    public Integer getSC_InOutStatus() {
        return SC_InOutStatus;
    }

    public void setSC_InOutStatus(Integer SC_InOutStatus) {
        this.SC_InOutStatus = SC_InOutStatus;
    }

    public DateTime getSC_RecordTime() {
        return SC_RecordTime;
    }

    public void setSC_RecordTime(DateTime SC_RecordTime) {
        this.SC_RecordTime = SC_RecordTime;
    }

    public DateTime getSC_AddTime() {
        return SC_AddTime;
    }

    public void setSC_AddTime(DateTime SC_AddTime) {
        this.SC_AddTime = SC_AddTime;
    }

    public String getSC_WorkerNO() {
        return SC_WorkerNO;
    }

    public void setSC_WorkerNO(String SC_WorkerNO) {
        this.SC_WorkerNO = SC_WorkerNO;
    }

    public String getSC_Name() {
        return SC_Name;
    }

    public void setSC_Name(String SC_Name) {
        this.SC_Name = SC_Name;
    }

    public String getSC_MobileNO() {
        return SC_MobileNO;
    }

    public void setSC_MobileNO(String SC_MobileNO) {
        this.SC_MobileNO = SC_MobileNO;
    }

    public Integer getSC_IDTypeID() {
        return SC_IDTypeID;
    }

    public void setSC_IDTypeID(Integer SC_IDTypeID) {
        this.SC_IDTypeID = SC_IDTypeID;
    }

    public Integer getSC_DepartmentID() {
        return SC_DepartmentID;
    }

    public void setSC_DepartmentID(Integer SC_DepartmentID) {
        this.SC_DepartmentID = SC_DepartmentID;
    }

    public String getSC_DepartmentName() {
        return SC_DepartmentName;
    }

    public void setSC_DepartmentName(String SC_DepartmentName) {
        this.SC_DepartmentName = SC_DepartmentName;
    }

    @Override
    public String toString() {
        return "OriginalRecord{" +
                "SC_SerierNO=" + SC_SerierNO +
                ", SC_DoorNO=" + SC_DoorNO +
                ", SC_CardGuidNO=" + SC_CardGuidNO +
                ", SC_EventTypeID=" + SC_EventTypeID +
                ", SC_InOutStatus=" + SC_InOutStatus +
                ", SC_RecordTime=" + SC_RecordTime +
                ", SC_AddTime=" + SC_AddTime +
                ", SC_WorkerNO='" + SC_WorkerNO + '\'' +
                ", SC_Name='" + SC_Name + '\'' +
                ", SC_MobileNO='" + SC_MobileNO + '\'' +
                ", SC_IDTypeID=" + SC_IDTypeID +
                ", SC_DepartmentID=" + SC_DepartmentID +
                ", SC_DepartmentName='" + SC_DepartmentName + '\'' +
                '}';
    }
}