package edu.iu.c322.trackingservice.model;

import jakarta.validation.constraints.NotEmpty;

public class Update {
    private int itemId;

    private String status;



    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
