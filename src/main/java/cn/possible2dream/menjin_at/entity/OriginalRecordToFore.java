package cn.possible2dream.menjin_at.entity;

import java.util.List;

public class OriginalRecordToFore {

    private int messageType;

    private List<OriginalRecord> listOriginalRecord;

    public int getMessageType() {
        return messageType;
    }

    public void setMessageType(int messageType) {
        this.messageType = messageType;
    }

    public List<OriginalRecord> getListOriginalRecord() {
        return listOriginalRecord;
    }

    public void setListOriginalRecord(List<OriginalRecord> listOriginalRecord) {
        this.listOriginalRecord = listOriginalRecord;
    }

    public OriginalRecordToFore() {

    }

    public OriginalRecordToFore(int messageType, List<OriginalRecord> listOriginalRecord) {
        this.messageType = messageType;
        this.listOriginalRecord = listOriginalRecord;
    }
}
