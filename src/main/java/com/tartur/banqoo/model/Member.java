package com.tartur.banqoo.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Member class models a user membership within an account management team.
 * So a User could have only one membership by account, and could have many related memberships.
 * A membership contains the user role, is he the Administrator or a writer or just a reader.
 * A member is though identified by the user and the account.
 * User: tartur
 * Date: 12/1/12
 * Time: 2:15 AM
 */
@Entity
@XmlRootElement
public class Member implements Identifiable<MemberId>{
    @EmbeddedId
    private MemberId id;
    @MapsId("username")
    @ManyToOne
    private User user;
    @MapsId("accountId")
    @ManyToOne
    private Account account;
    @NotNull
    @Enumerated(EnumType.STRING)
    private MemberRole role;

    public Member() {

    }

    public Member(User user, MemberRole role, Account account) {
        id = new MemberId();
        this.user = user;
        this.account = account;
        this.role = role;
    }

    @Override
    public MemberId getId() {
        return id;
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

        if (account != null ? !account.equals(member.account) : member.account != null) return false;
        if (user != null ? !user.equals(member.user) : member.user != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = user != null ? user.hashCode() : 0;
        result = 31 * result + (account != null ? account.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", role=" + role +
                '}';
    }

    public static enum MemberRole {
        Admin, Writer, Reader
    }
}
