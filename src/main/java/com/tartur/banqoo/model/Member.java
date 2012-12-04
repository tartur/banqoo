package com.tartur.banqoo.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created with IntelliJ IDEA.
 * User: tartur
 * Date: 12/1/12
 * Time: 2:15 AM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@IdClass(MemberId.class)
@XmlRootElement
public class Member {
    @Id
    @ManyToOne
    private User user;
    @Id
    @ManyToOne
    private Account account;
    @NotNull
    @Enumerated(EnumType.STRING)
    private MemberRole role;

    public Member() {

    }

    public Member(User user, MemberRole role, Account account) {
        this.user = user;
        this.role = role;
        this.account = account;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public MemberRole getRole() {
        return role;
    }

    public void setRole(MemberRole role) {
        this.role = role;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Member member = (Member) o;

        if (!account.equals(member.account)) return false;
        if (!user.equals(member.user)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = user.hashCode();
        result = 31 * result + account.hashCode();
        return result;
    }

    public static enum MemberRole {
        Admin, Writer, Reader
    }
}
