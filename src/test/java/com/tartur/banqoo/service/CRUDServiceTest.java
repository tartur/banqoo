package com.tartur.banqoo.service;

import com.tartur.banqoo.model.User;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import static org.junit.Assert.assertNotNull;

/**
 * Created with IntelliJ IDEA.
 * User: tartur
 * Date: 12/2/12
 * Time: 2:18 AM
 * To change this template use File | Settings | File Templates.
 */
public class CRUDServiceTest {


    private EntityManagerFactory emf;
    private CRUDService sut;

    @Before
    public void setUp() {
        emf = Persistence.createEntityManagerFactory("test");
        sut = new CRUDService();
        sut.setEntityManagerFactory(emf);
    }

    @Test
    public void createNewUser() {
        User u = sut.create(new User("tartur", "kjjkl", "tart@tur.tt"));
        assertNotNull(u);
    }

//    @Deployment
//    public static JavaArchive createDeployment() {
//        return ShrinkWrap.create(JavaArchive.class)
//                .addClass(Greeter.class)
//                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
//    }
}
