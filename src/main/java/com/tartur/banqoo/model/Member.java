package com.tartur.banqoo.model;

/**
 * Created with IntelliJ IDEA.
 * User: tartur
 * Date: 12/1/12
 * Time: 2:15 AM
 * To change this template use File | Settings | File Templates.
 */
public class Member {
    private User user;
    private MemberRole role;


    public static enum MemberRole {
        Admin, Writer, Reader
    }
}
