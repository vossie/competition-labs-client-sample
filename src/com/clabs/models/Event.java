package com.clabs.models;

import java.util.Date;

final public class Event {

    public String memberRefId;

    public String entityRefId;

    public String eventRefId;

    public String action;

    public Float sourceValue;

    public Date transactionTimestamp;

    public Event setMemberRefId(String memberRefId) {
        this.memberRefId = memberRefId;
        return this;
    }

    public Event setEntityRefId(String entityRefId) {
        this.entityRefId = entityRefId;
        return this;
    }

    public Event setEventRefId(String eventRefId) {
        this.eventRefId = eventRefId;
        return this;
    }

    public Event setAction(String action) {
        this.action = action;
        return this;
    }

    public Event setSourceValue(Float sourceValue) {
        this.sourceValue = sourceValue;
        return this;
    }

    public Event setTransactionTimestamp(Date transactionTimestamp) {
        this.transactionTimestamp = transactionTimestamp;
        return this;
    }
}
