package com.lianne.xml.audit.authorization;

import lombok.*;

import com.lianne.xml.XMLAuditBaseObject;

import java.text.SimpleDateFormat;
import java.text.ParseException;

@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper=true, includeFieldNames=true)
public class AuditAuthorization extends XMLAuditBaseObject {
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

    /* due to lombok
    public String getTransactionStatusCode() {
        return transactionStatusCode;
    }

    public void setTransactionStatusCode(String transactionStatusCode) {
        this.transactionStatusCode = transactionStatusCode;
    }
    */

    /* due to lombok
    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
     */

    /* due to lombok
    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }
     */

    @ToString.Include
    public String getDuration() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss.SSS");
        try {
            return (formatter.parse(getEndTime()).getTime() -
                    formatter.parse(getStartTime()).getTime()) + "ms";
        } catch (ParseException ex) {
            return "It is impossible to calculate duration due to error";
        }
    }

    /* due to lombok
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
     */
}
