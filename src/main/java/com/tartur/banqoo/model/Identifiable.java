package com.tartur.banqoo.model;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: tartur
 * Date: 12/5/12
 * Time: 2:37 AM
 * To change this template use File | Settings | File Templates.
 */
public interface Identifiable<T extends Serializable> {
    T getId();
}
