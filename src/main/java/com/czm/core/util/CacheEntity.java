package com.czm.core.util;

import java.io.Serializable;

public class CacheEntity implements Serializable {

    private String key;
    private Object content;
    private long outTime;

    public CacheEntity() {
    }

    public CacheEntity(String key) {
    }

    public CacheEntity(String key, Object content) {
        this.key = key;
        this.content = content;
    }

    public long getOutTime() {
        return outTime;
    }

    public void setOutTime(long outTime) {
        this.outTime = outTime;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "CacheEntity{" +
                "key='" + key + '\'' +
                ", content=" + content +
                ", outTime=" + outTime +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CacheEntity)) return false;

        CacheEntity that = (CacheEntity) o;

        if (getOutTime() != that.getOutTime()) return false;
        if (!getKey().equals(that.getKey())) return false;
        return getContent().equals(that.getContent());
    }

    @Override
    public int hashCode() {
        int result = getKey().hashCode();
        result = 31 * result + getContent().hashCode();
        result = 31 * result + (int) (getOutTime() ^ (getOutTime() >>> 32));
        return result;
    }
}
