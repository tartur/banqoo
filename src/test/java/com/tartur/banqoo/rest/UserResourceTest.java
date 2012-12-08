package com.tartur.banqoo.rest;

import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.test.framework.JerseyTest;
import com.sun.jersey.test.framework.spi.container.TestContainerException;
import com.tartur.banqoo.model.User;
import com.tartur.banqoo.service.DataService;
import org.jglue.cdiunit.CdiRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.skyscreamer.jsonassert.JSONAssert;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.ws.rs.core.MediaType;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.*;

/**
 * This test class is intended to test UserResource rest services
 *
 * @author : tartur
 *         Date: 12/6/12
 *         Time: 12:58 AM
 */
@RunWith(CdiRunner.class)
public class UserResourceTest extends JerseyTest {
    @Inject
    private UserResource sut;

    @Produces
    @Mock
    private DataService dataSvc;

    public UserResourceTest() throws TestContainerException {
        super(UserResource.class.getPackage().getName());
    }

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void dummyTest() throws Exception {
        when(dataSvc.findUserByUsername("tartur")).thenReturn(new User("tartur","coucou", "tartur@gmail.com"));

        User user = sut.get();

        assertThat(user).isEqualTo(new User("tartur","coucou", "tartur@gmail.com"));
        WebResource rs = resource();

        String result = rs.path("/users").accept(MediaType.APPLICATION_JSON_TYPE).get(String.class);

        JSONAssert.assertEquals("{\"username\":\"tartur\", \"emailAddress\":\"tartur@gmail.com\", \"password\":\"coucou\"}", result, false);
    }
}
