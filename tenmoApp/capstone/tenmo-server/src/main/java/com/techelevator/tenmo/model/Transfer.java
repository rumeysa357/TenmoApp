package com.techelevator.tenmo.model;

public class Transfer {
    private int transfer_id;
    private int transfers_type_id;
    private int transfers_status_id;
    private int account_from;
    private int account_to;
    private double amount;
    private String usernameTo;
    private String usernameFrom;
    private int from_User_Id;
    private int to_User_Id;

    public Transfer( int transfers_type_id, int transfers_status_id, int account_from, int account_to, double amount, String transfer_type_desc) {
        this.transfers_type_id = transfers_type_id;
        this.transfers_status_id = transfers_status_id;
        this.account_from = account_from;
        this.account_to = account_to;
        this.amount = amount;
    }

    public Transfer() {
    }

    public int getTransfer_id() {
        return transfer_id;
    }

    public void setTransfer_id(int transfer_id) {
        this.transfer_id = transfer_id;
    }

    public String getUsernameFrom() {
        return usernameFrom;
    }

    public void setUsernameFrom(String usernameFrom) {
        this.usernameFrom = usernameFrom;
    }

    public int getFrom_User_Id() {
        return from_User_Id;
    }

    public void setFrom_User_Id(int from_User_Id) {
        this.from_User_Id = from_User_Id;
    }

    public int getTo_User_Id() {
        return to_User_Id;
    }

    public void setTo_User_Id(int to_User_Id) {
        this.to_User_Id = to_User_Id;
    }

    public String getUsernameTo() {
        return usernameTo;
    }

    public void setUsernameTo(String usernameTo) {
        this.usernameTo = usernameTo;
    }






    public int getTransfers_type_id() {
        return transfers_type_id;
    }

    public void setTransfers_type_id(int transfers_type_id) {
        this.transfers_type_id = transfers_type_id;
    }

    public int getTransfers_status_id() {
        return transfers_status_id;
    }

    public void setTransfers_status_id(int transfers_status_id) {
        this.transfers_status_id = transfers_status_id;
    }

    public int getAccount_from() {
        return account_from;
    }

    public void setAccount_from(int account_from) {
        this.account_from = account_from;
    }

    public int getAccount_to() {
        return account_to;
    }

    public void setAccount_to(int account_to) {
        this.account_to = account_to;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
