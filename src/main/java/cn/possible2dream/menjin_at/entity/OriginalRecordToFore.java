package cn.possible2dream.menjin_at.entity;

public class OriginalRecordToFore<T> {

    private int messageType;

    private T t;


    public int getMessageType() {
        return messageType;
    }

    public void setMessageType(int messageType) {
        this.messageType = messageType;
    }

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }

    public OriginalRecordToFore() {

    }

    public OriginalRecordToFore(int messageType, T t) {
        this.messageType = messageType;
        this.t = t;
    }
}
