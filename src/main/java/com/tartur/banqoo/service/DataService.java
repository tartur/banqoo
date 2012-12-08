package com.tartur.banqoo.service;

import com.tartur.banqoo.model.Account;
import com.tartur.banqoo.model.Identifiable;
import com.tartur.banqoo.model.User;

/**
 * This interface defines Data Layer Service API.
 * User: tartur
 * Date: 12/6/12
 * Time: 11:48 PM
 */
public interface DataService {

    <T extends Identifiable<?>> T create(T entity);

    Account createAccount(Account account);

    User findUserByUsername(String username);

    User findUserByEmail(String email);

    <T extends Identifiable<?>> void update(T entity);

    Account findAccountByOwnerAndName(User owner, String accountName);
}
