package com.techelevator.tenmo.controller;


import com.techelevator.tenmo.dao.AccountDAO;
import com.techelevator.tenmo.dao.TransferDAO;
import com.techelevator.tenmo.dao.UserDAO;
import com.techelevator.tenmo.exception.UserNotFoundException;
import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.User;
import com.techelevator.tenmo.security.UserNotActivatedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.swing.*;
import java.security.Principal;
import java.util.List;


@PreAuthorize("isAuthenticated()")
@RestController
public class AccountController {


    @Autowired
    private AccountDAO dao;

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private TransferDAO transferDAO;

    @PreAuthorize("permitAll")
    @RequestMapping(path = "accounts/{user_id}", method = RequestMethod.GET)
    public Account retrieveBalanceByUserId(@PathVariable int user_id) throws UserNotFoundException {
        return dao.getAccountBalance(user_id);
    }

    @PreAuthorize("permitAll")
    @RequestMapping(path = "accounts", method = RequestMethod.GET)
    public List<Account> retrieveAccounts() {
        return dao.retrieveListOfAccounts();
    }


//   // @PreAuthorize("permitAll")
//    @RequestMapping(path = "transfers/{account_from}", method = RequestMethod.GET)
//    public List<Transfer> getTransfers(@PathVariable int account_from) throws UserNotFoundException {  //principal
//
//        return transferDAO.retrieveTransferList(account_from);}


    @PreAuthorize("permitAll")
    @RequestMapping(path = "users/{username}", method = RequestMethod.GET)
    public User getUserByName(@PathVariable String username, Principal principal) throws UserNotFoundException{
        return userDAO.findByUsername(username);
    }
//        auditLog("delete",id,principal.getName());

    @PreAuthorize("permitAll")
    @RequestMapping(path = "transfers/{user_id}", method = RequestMethod.GET)
    public List<Transfer> getTransfers(@PathVariable int user_id) throws UserNotFoundException {  //principal
        return transferDAO.retrieveTransferList(user_id);
        //in the authentication lecture jghomes the delete method uses the principal object

    }

    @PreAuthorize("permitAll")
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "transfers", method = RequestMethod.POST)
    public Transfer addTransfer( @RequestBody Transfer transfer)throws UserNotFoundException {
        return transferDAO.makeTransfer(transfer);
    }


    @PreAuthorize("permitAll")
    @RequestMapping(path = "usersid/{username}", method = RequestMethod.GET)
    public int getUserIdByName(@PathVariable String username,Principal principal) throws UserNotFoundException{
        return userDAO.findIdByUsername(username);
    }

    @PreAuthorize("permitAll")
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "accounts/{account_id}", method = RequestMethod.PUT)
    public Account updateAccount( @RequestBody Account account,@PathVariable int account_id)throws UserNotFoundException {
        return dao.updateAccount(account,account_id);
    }


}