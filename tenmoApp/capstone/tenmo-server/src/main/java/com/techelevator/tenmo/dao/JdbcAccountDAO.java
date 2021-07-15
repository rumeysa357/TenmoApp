package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.exception.UserNotFoundException;
import com.techelevator.tenmo.model.Account;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component

public class JdbcAccountDAO implements AccountDAO {

    private JdbcTemplate jdbcTemplate;

    public JdbcAccountDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Account getAccountBalance(int user_id) throws UserNotFoundException {
Account account = null;
String sql = "SELECT accounts.account_id, users.user_id, accounts.balance, users.username FROM accounts\n" +
        "JOIN users ON users.user_id = accounts.user_id \n" +
        "WHERE users.user_id =? ;";
        SqlRowSet rows = jdbcTemplate.queryForRowSet(sql,user_id);
        if(rows.next()) {
            account=mapRowAccount(rows);
        }
        if( account==null){
            throw new UserNotFoundException();
        }
        return account;

    }

    @Override
    public List<Account> retrieveListOfAccounts() {
        List<Account> accounts=new ArrayList<>();

        String sql="SELECT accounts.account_id, users.user_id, accounts.balance,users.username\n" +
                " FROM accounts\n" +
                " JOIN users ON users.user_id = accounts.user_id";

        SqlRowSet rows = jdbcTemplate.queryForRowSet(sql);

        while (rows.next()) {

            Account account = mapRowAccount(rows);
            accounts.add(account);
        }
        return accounts;
    }
        @Override
    public Account findAccountbyAccountID(int  account_id) throws UserNotFoundException {
        String sql = "SELECT account_id,user_id, balance FROM accounts  WHERE account_id= ?;";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, account_id);
        if (rowSet.next()) {
            mapRowAccount1(rowSet);
        }
        return mapRowAccount1((rowSet));
    }

    @Override
    public Account updateAccount(Account account, int account_id) throws UserNotFoundException {

        String updateUserBalanceSql = "UPDATE accounts SET account_id =?, user_id = ?, balance = ? WHERE account_id = ?";
        jdbcTemplate.update(updateUserBalanceSql,account.getAccount_id(),account.getUser_id(),account.getBalance(),account.getAccount_id());

        return account;


    }

    private Account mapRowAccount1(SqlRowSet rows){
        Account account = new Account();
        account.setAccount_id(rows.getInt("account_id"));
        account.setUser_id(rows.getInt("user_id"));
        account.setBalance(rows.getDouble("balance"));
        return account;
    }

    private Account mapRowAccount(SqlRowSet rows){
        Account account = new Account();
        account.setAccount_id(rows.getInt("account_id"));
        account.setUser_id(rows.getInt("user_id"));
        account.setBalance(rows.getDouble("balance"));
        account.setUser_name(rows.getString("username"));
return account;
    }
}
