package com.tartur.banqoo.model;

import java.io.Serializable;

/**
 * This interface labels entity classes as identifiable and therefore should have
 * <code>T getId()</code> method
 * @author: tartur
 * @Date: 12/5/12
 * @Time: 2:37 AM
 */
public interface Identifiable<T extends Serializable> {
    T getId();
}
