package edu.iu.c322.trackingservice.model;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;

public class ItemStatus {
    private String status;
    private String date;

    public ItemStatus() {
        Format f = new SimpleDateFormat("MM/dd/yy");
        date = f.format(new Date());

    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
