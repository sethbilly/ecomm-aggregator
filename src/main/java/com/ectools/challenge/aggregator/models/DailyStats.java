package com.ectools.challenge.aggregator.models;

import java.io.Serializable;
import java.util.Date;

public class DailyStats implements Serializable {

    private Date day;
    private int created;
    private int updated;

    public Date getDay() {
        return day;
    }

    public void setDay(Date day) {
        this.day = day;
    }

    public int getCreated() {
        return created;
    }

    public void setCreated(int created) {
        this.created = created;
    }

    public int getUpdated() {
        return updated;
    }

    public void setUpdated(int updated) {
        this.updated = updated;
    }
}
