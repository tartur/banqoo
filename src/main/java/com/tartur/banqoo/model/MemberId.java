package com.tartur.banqoo.model;

import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * This class models the Member class composite identifier.
 * User: tartur
 * Date: 12/4/12
 * Time: 12:52 AM
 * @see Member
 */
@Embeddable
public class MemberId implements Serializable {
    String username;
    Long accountId;

    public MemberId() {
    }

    public MemberId(User user, Account account) {
        this.username = user.getUsername();
        this.accountId = account.getId();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MemberId memberId = (MemberId) o;

        if (accountId != null ? !accountId.equals(memberId.accountId) : memberId.accountId != null) return false;
        if (username != null ? !username.equals(memberId.username) : memberId.username != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = username != null ? username.hashCode() : 0;
        result = 31 * result + (accountId != null ? accountId.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "MemberId{" +
                "username='" + username + '\'' +
                ", accountId=" + accountId +
                '}';
    }
}
