package com.lianne.xml.audit.authorization;

import com.lianne.xml.XMLObject;

import java.text.SimpleDateFormat;
import java.text.ParseException;

public class AuditAuthorization extends XMLObject {
    private String startTime;
    private String endTime;
    private String transactionStatusCode;

    public AuditAuthorization() {
        super();
    }

    public AuditAuthorization(AuditAuthorization audit) {
        super(audit.getId());
        setStartTime(audit.getStartTime());
        setEndTime(audit.getEndTime());
        setTransactionStatusCode(audit.getTransactionStatusCode());
    }

    public void clear() {
        super.clear();
        startTime = "";
        endTime = "";
        transactionStatusCode = null;
    }
    public String getTransactionStatusCode() {
        return transactionStatusCode;
    }

    public void setTransactionStatusCode(String transactionStatusCode) {
        this.transactionStatusCode = transactionStatusCode;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getDuration() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss.SSS");
        try {
            return (formatter.parse(getEndTime()).getTime() -
                    formatter.parse(getStartTime()).getTime()) + "ms";
        } catch (ParseException ex) {
            return "";
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Audit [id=");
        builder.append(getId());
        builder.append(", code = ");
        builder.append(getTransactionStatusCode());
        builder.append(", start time = ");
        builder.append(getStartTime());
        builder.append(", end time = ");
        builder.append(getEndTime());
        builder.append(", duration = ");
        builder.append(getDuration());
        builder.append("]");
        return builder.toString();

    }
}
