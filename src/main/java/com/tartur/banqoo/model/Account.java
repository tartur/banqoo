package com.tartur.banqoo.model;

import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Money account model class. It is not the user account on the application, but an account created by a user
 * in order to do accounting job with it.
 * User: tartur
 * Date: 12/1/12
 * Time: 2:13 AM
 */
@Entity
@XmlRootElement
public class Account implements Identifiable<Long>{
    @Id
    @GenericGenerator(name = "account_kgen", strategy = "increment")
    @GeneratedValue(generator = "account_kgen")
    private Long id;
    @NotNull
    private String name;
    @NotNull
    @ManyToOne
    private User owner;
    @OneToMany(mappedBy = "account")
    private Set<Member> team;
    private Date creationDate = new Date();

    public Account() {
        this(null, null);
    }

    public Account(String name, User owner) {
        this.name = name;
        this.owner = owner;
        team = new HashSet<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    /**
     * Inserts the new member if it does not exist
     * @param member
     * @return true if the member was successfully added
     */
    public boolean put(Member member) {
        return team.add(member);
    }

    public Set<Member> getTeam() {
        return Collections.unmodifiableSet(team);
    }

    /**
     * Removes a member from the team
     * @param member
     * @return
     */
    public boolean remove(Member member) {
        return team.remove(member);
    }

    public boolean isTeamMember(User user) {
        return Collections2.transform(team, new Function<Member, User>() {
            @Override
            public User apply(Member member) {
                return member == null ? null : member.getUser();
            }
        }).contains(user);
    }

    public Member.MemberRole getRole(User user) {
        for (Member m : team) {
            if (m != null && m.getUser().equals(user)) {
                return m.getRole();
            }
        }
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Account account = (Account) o;

        if (id != null ? !id.equals(account.id) : account.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", owner=" + owner +
                '}';
    }
}
