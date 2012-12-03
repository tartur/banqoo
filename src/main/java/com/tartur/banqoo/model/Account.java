package com.tartur.banqoo.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: tartur
 * Date: 12/1/12
 * Time: 2:13 AM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@XmlRootElement
public class Account {
    @Id
    private Long id;
    private String name;
    @ManyToOne
    private User owner;
    private Date creationDate = new Date();




}
