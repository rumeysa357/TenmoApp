package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.exception.UserNotFoundException;
import com.techelevator.tenmo.model.Account;

import java.util.List;

public interface AccountDAO {
    public Account getAccountBalance(int user_id) throws UserNotFoundException;


    List<Account> retrieveListOfAccounts();

    public Account findAccountbyAccountID(int  account_id) throws UserNotFoundException;

    public Account updateAccount(Account account,int account_id)  throws UserNotFoundException;


}
