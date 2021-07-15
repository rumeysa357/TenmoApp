package com.techelevator.tenmo.services;

import com.techelevator.tenmo.exceptions.AccountServiceException;
import com.techelevator.tenmo.models.Account;
import com.techelevator.tenmo.models.Transfer;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

public class AccountService {
    private final String BASE_SERVICE_URL;
    public String AUTH_TOKEN = "";
    private RestTemplate restTemplate = new RestTemplate();

    public AccountService(String baseUrl) {
        this.BASE_SERVICE_URL = baseUrl;
    }

    public Account retrieveAccountByUserID(int user_id) throws AccountServiceException {
        Account account = null;
        // create HTTP header so we can set application/json AND AUTH_TOKEN
        // build and entity object so the header gets added
        // call the web service using the exchange method

        try {
            // Below is how we did it before. BUT getForObject does not allow for an Entity object so we are forced to use
            // the exchange method below IF we want authentication turned on. If we don't need authentication, we can use
            // getForObject() method.
            //home = restTemplate.getForObject(BASE_URL + "homes", Home.class);

            account= restTemplate.exchange(BASE_SERVICE_URL + "accounts/" + user_id, HttpMethod.GET, makeAccountEntity(account), Account.class).getBody();
        } catch (RestClientResponseException ex) {
            if(ex.getRawStatusCode() == 404) {
                return null;
            }
            throw new AccountServiceException(ex.getRawStatusCode() + " : " + ex.getResponseBodyAsString());
        }

        return account;

    }

    public void setAUTH_TOKEN(String aUTH_TOKEN) {

        AUTH_TOKEN = aUTH_TOKEN;
    }

    public Account[] retrieveAccounts() {

        Account[] accountsFound = null;

        try {
            accountsFound = restTemplate.exchange(BASE_SERVICE_URL+ "accounts/",HttpMethod.GET, makeAuthEntity(), Account[].class).getBody();

        } catch (RestClientResponseException ex) {
            // TODO 404, something else kinda error
        }

        return accountsFound;
    }

    public Transfer makeTransfers(Transfer transfer) {

        try {
            transfer=restTemplate.exchange(BASE_SERVICE_URL + "transfers" , HttpMethod.POST, makeTransferEntity(transfer), Transfer.class).getBody();
        } catch (RestClientResponseException ex) {
            // TODO
        }
       return transfer;

    }

    public Transfer[] retrieveTransferList(int user_id) throws AccountServiceException {
        Transfer[] transfers = null;

        try {

            transfers = restTemplate.exchange(BASE_SERVICE_URL + "transfers/" + user_id, HttpMethod.GET, makeAuthEntity(), Transfer[].class).getBody();
        } catch (RestClientResponseException ex) {
            if(ex.getRawStatusCode() == 404) {
                return null;
            }
            throw new AccountServiceException(ex.getRawStatusCode() + " : " + ex.getResponseBodyAsString());
        }

        return transfers;

    }





    private HttpEntity<Account> makeAccountEntity(Account account) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(AUTH_TOKEN);
        HttpEntity<Account> entity = new HttpEntity<>(account, headers);
        return entity;
    }
    private HttpEntity<Transfer> makeTransferEntity(Transfer transfer) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(AUTH_TOKEN);
        HttpEntity<Transfer> entity = new HttpEntity<>(transfer, headers);
        return entity;
    }
    // MAKE AN AUTH ENTITY ONLY
    private HttpEntity makeAuthEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(AUTH_TOKEN);
        HttpEntity entity = new HttpEntity<>(headers);
        return entity;
    }



}
