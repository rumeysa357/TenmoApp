package com.techelevator.tenmo.models;

public class TransferStatuses {
    private  int transfer_status_id;
    private String transfer_status_disc;

    public TransferStatuses(int transfer_status_id, String transfer_status_disc) {
        this.transfer_status_id = transfer_status_id;
        this.transfer_status_disc = transfer_status_disc;
    }

    public TransferStatuses() {
    }

    public int getTransfer_status_id() {
        return transfer_status_id;
    }

    public void setTransfer_status_id(int transfer_status_id) {
        this.transfer_status_id = transfer_status_id;
    }

    public String getTransfer_status_disc() {
        return transfer_status_disc;
    }

    public void setTransfer_status_disc(String transfer_status_disc) {
        this.transfer_status_disc = transfer_status_disc;
    }
}
