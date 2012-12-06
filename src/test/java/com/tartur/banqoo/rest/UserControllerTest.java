package com.tartur.banqoo.rest;

import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.test.framework.JerseyTest;
import com.sun.jersey.test.framework.spi.container.TestContainerException;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;

import javax.ws.rs.core.MediaType;

/**
 * This test class is intended to test UserController rest services
 *
 * @author : tartur
 *         Date: 12/6/12
 *         Time: 12:58 AM
 */
public class UserControllerTest extends JerseyTest {

    public UserControllerTest() throws TestContainerException {
        super(UserController.class.getPackage().getName());
    }

    @Test
    public void dummyTest() throws Exception {
        WebResource rs = resource();
        String result = rs.path("/user").accept(MediaType.APPLICATION_JSON_TYPE).get(String.class);
        JSONAssert.assertEquals("{\"username\":\"tartur\", \"emailAddress\":\"tartur@gmail.com\", \"password\":\"coucou\"}", result, false);
    }
}
