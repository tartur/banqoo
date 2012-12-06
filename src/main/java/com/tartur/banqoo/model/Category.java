package com.tartur.banqoo.model;

import java.util.Set;

/**
 * Category class models an operation category. It is created by a user and associated to multiple.
 * One category could have multiple subcategories.
 * User: tartur
 * Date: 12/1/12
 * Time: 2:31 AM
 */
public class Category {
    private String name;
    private Set<Category> subcategories;
}
