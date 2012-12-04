package com.tartur.banqoo.model;

import javax.persistence.Embeddable;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: tartur
 * Date: 12/4/12
 * Time: 12:52 AM
 * To change this template use File | Settings | File Templates.
 */
public class MemberId implements Serializable {
    @ManyToOne
    private User user;
    @ManyToOne
    private Account account;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MemberId memberId = (MemberId) o;

        if (!account.equals(memberId.account)) return false;
        if (!user.equals(memberId.user)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = user.hashCode();
        result = 31 * result + account.hashCode();
        return result;
    }
}
