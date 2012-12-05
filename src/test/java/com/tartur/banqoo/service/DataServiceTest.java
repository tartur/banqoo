package com.tartur.banqoo.service;

import com.tartur.banqoo.model.Account;
import com.tartur.banqoo.model.Member;
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
    private User mockUser;

    @BeforeClass
    public static void setUpClass() {
        emf = Persistence.createEntityManagerFactory("test");
    }

    @Before
    public void setUp() {
        sut = new DataService();
        sut.setEntityManagerFactory(emf);
        mockUser = sut.findUserByUsername("mockUser");
    }

    @Test
    public void createNewUser() {
        User u = sut.create(new User("tartur", "kjjkl", "tart@tur.tt"));
        assertNotNull(u);
    }

    @Test
    public void findByUsernameExistingUser() {
        assertThat(mockUser).isNotNull();
    }

    @Test
    public void returnsNullWhenNoUserIsFoundByUsername() {
        User u = sut.findUserByUsername("mock1User");
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
        String oldPwd = mockUser.getPassword();
        String newPwd = oldPwd + "1";
        mockUser.setPassword(newPwd);
        sut.update(mockUser);
        User updated = sut.findUserByUsername("mockUser");
        assertThat(updated.getPassword()).isEqualTo(newPwd);
    }

    @Test
    public void userCreateAnAccount() {
        Account acc = new Account("hisAccount", mockUser);
        assertThat(acc.isTeamMember(mockUser)).isFalse();
        assertThat(acc.getId()).isNull();

        acc = sut.createAccount(acc);

        assertThat(acc.getId()).isGreaterThan(0);
        assertThat(acc.isTeamMember(mockUser)).isTrue();
        assertThat(acc.getRole(mockUser)).isEqualTo(Member.MemberRole.Admin);
    }

    @Test
    public void findAnAccountByUserAndName() {
        Account acc = sut.findAccountByOwnerAndName(mockUser, "mockAccount");
        assertThat(acc).isNotNull();
        assertThat(acc.getId()).isEqualTo(1);
    }

    @Test
    public void returnsNullWhenAccountIsNotFound() {
        Account acc = sut.findAccountByOwnerAndName(mockUser, "otherAccount");
        assertThat(acc).isNull();
    }

    @Test
    public void userLogOperationIntoAccount() {
        //TODO
    }

}
