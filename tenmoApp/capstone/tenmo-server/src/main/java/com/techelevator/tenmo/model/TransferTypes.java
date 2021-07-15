package com.techelevator.tenmo.model;

public class TransferTypes {
    private  int transfer_type_id;
    private String transfer_type_disc;

    public TransferTypes(int transfer_type_id, String transfer_type_disc) {
        this.transfer_type_id = transfer_type_id;
        this.transfer_type_disc = transfer_type_disc;
    }

    public TransferTypes() {
    }

    public int getTransfer_type_id() {
        return transfer_type_id;
    }

    public void setTransfer_type_id(int transfer_type_id) {
        this.transfer_type_id = transfer_type_id;
    }

    public String getTransfer_type_disc() {
        return transfer_type_disc;
    }

    public void setTransfer_type_disc(String transfer_type_disc) {
        this.transfer_type_disc = transfer_type_disc;
    }
}
