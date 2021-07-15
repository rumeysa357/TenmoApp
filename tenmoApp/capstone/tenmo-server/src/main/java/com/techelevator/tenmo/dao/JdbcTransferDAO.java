package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.exception.UserNotFoundException;
import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transfer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcTransferDAO implements TransferDAO {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private AccountDAO accountDAO;

    public JdbcTransferDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Transfer makeTransfer(Transfer transfer) throws UserNotFoundException {
        String transferSQL = "INSERT INTO transfers ( transfer_type_id, transfer_status_id, account_from, account_to, amount) VALUES (?, ?, ?, ?, ?);\n";
        jdbcTemplate.update(transferSQL,
                transfer.getTransfers_type_id(),
                transfer.getTransfers_status_id(),
                transfer.getAccount_from(),
                transfer.getAccount_to(),
                transfer.getAmount());


        Account accountFrom=accountDAO.findAccountbyAccountID(transfer.getAccount_from());
        accountFrom.setBalance(accountFrom.getBalance()-transfer.getAmount());

        Account accountTo=accountDAO.findAccountbyAccountID(transfer.getAccount_to());
        accountTo.setBalance(accountTo.getBalance()+transfer.getAmount());


        String updateUserBalanceSql = "UPDATE accounts SET  balance = ? WHERE account_id = ?";
        jdbcTemplate.update(updateUserBalanceSql,accountFrom.getBalance(),accountFrom.getAccount_id());

        String sqlReceiverBalanceSql="UPDATE accounts SET balance = ? WHERE account_id = ?";
        jdbcTemplate.update(sqlReceiverBalanceSql,accountTo.getBalance(),accountTo.getAccount_id());




        return transfer;
    }

    @Override
    public List<Transfer> retrieveTransferList(int user_id) {
        List<Transfer> transfers = new ArrayList<Transfer>();

        String sql = "SELECT transfers.*, from_users.user_id AS user_id_from, to_users.user_id AS user_id_to, from_users.username AS username_from, to_users.username AS username_to\n" +
                "FROM transfers\n" +
                "JOIN accounts AS from_accounts ON transfers.account_from = from_accounts.account_id\n" +
                "JOIN accounts AS to_accounts ON transfers.account_to = to_accounts.account_id\n" +
                "JOIN users AS from_users ON from_accounts.user_id = from_users.user_id\n" +
                "JOIN users AS to_users ON to_accounts.user_id = to_users.user_id\n" +
                "WHERE from_users.user_id =? OR to_users.user_id = ?;";

        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql,user_id,user_id);


        while (rowSet.next()) {
            Transfer transfer = mapRowToTransfer(rowSet);
            transfer.setTransfer_id(rowSet.getInt("transfer_id"));

            transfers.add(transfer);
        }

        return transfers;
    }

    private Transfer mapRowToTransfer(SqlRowSet rows) {
        Transfer transfer = new Transfer();
        transfer.setTransfers_type_id(rows.getInt("transfer_type_id"));
        transfer.setTransfers_status_id(rows.getInt("transfer_status_id"));
        transfer.setAccount_from(rows.getInt("account_from"));
        transfer.setAccount_to(rows.getInt("account_to"));
        transfer.setAmount(rows.getDouble("amount"));
        transfer.setUsernameFrom(rows.getString("username_from"));
        transfer.setUsernameTo(rows.getString("username_to"));
        transfer.setFrom_User_Id(rows.getInt("user_id_from"));
        transfer.setTo_User_Id(rows.getInt("user_id_to"));

        return transfer;
    }


}



