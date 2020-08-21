package com.open.status.trasfer;

import java.io.Serializable;

public class ProtoData implements Serializable {

    private final Integer statusValue;

    private final String statusData;

    public ProtoData(Integer statusValue, String statusData) {
        this.statusValue = statusValue;
        this.statusData = statusData;
    }

    public Integer getStatusValue() {
        return statusValue;
    }

    public String getStatusData() {
        return statusData;
    }

    @Override
    public String toString() {
        return "ProtoData{" +
                "statusValue=" + statusValue +
                ", statusData='" + statusData + '\'' +
                '}';
    }
}
