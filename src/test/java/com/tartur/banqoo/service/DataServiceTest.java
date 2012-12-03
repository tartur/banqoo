package com.tartur.banqoo.service;

import com.tartur.banqoo.model.Account;
import com.tartur.banqoo.model.User;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.unitils.UnitilsJUnit4;
import org.unitils.dbunit.annotation.DataSet;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import static org.fest.assertions.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;

/**
 * Created with IntelliJ IDEA.
 * User: tartur
 * Date: 12/2/12
 * Time: 2:18 AM
 * To change this template use File | Settings | File Templates.
 */
@DataSet
public class DataServiceTest extends UnitilsJUnit4 {


    private static EntityManagerFactory emf;
    private DataService sut;

    @BeforeClass
    public static void setUpClass() {
        emf = Persistence.createEntityManagerFactory("test");
    }

    @Before
    public void setUp() {
        sut = new DataService();
        sut.setEntityManagerFactory(emf);
    }

    @Test
    public void createNewUser() {
        User u = sut.create(new User("tartur", "kjjkl", "tart@tur.tt"));
        assertNotNull(u);
    }

    @Test
    public void findByUsernameExistingUser() {
        User u = sut.findUserByUsername("mock");
        assertThat(u).isNotNull();
    }

    @Test
    public void returnsNullWhenNoUserIsFoundByUsername() {
        User u = sut.findUserByUsername("mock1");
        assertThat(u).isNull();
    }

    @Test
    public void findByEmailExistingUser() {
        User u = sut.findUserByEmail("mock@test.mo");
        assertThat(u).isNotNull();
    }

    @Test
    public void returnsNullWhenNoUserIsFoundByEmail() {
        User u = sut.findUserByEmail("unknown@test.mo");
        assertThat(u).isNull();
    }

    @Test
    public void updateExistingUser() {
        User u = sut.findUserByUsername("mock");
        String oldPwd = u.getPassword();
        String newPwd = oldPwd + "1";
        u.setPassword(newPwd);
        sut.update(u);
        User updated = sut.findUserByUsername("mock");
        assertThat(updated.getPassword()).isEqualTo(newPwd);
    }

    @Test
    public void userCreateAnAccount() {
        User u = sut.findUserByUsername("mock");
        Account acc = new Account();
        sut.create(acc);
    }

}