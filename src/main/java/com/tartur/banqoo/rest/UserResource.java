package com.tartur.banqoo.rest;

import com.tartur.banqoo.model.User;
import com.tartur.banqoo.service.DataService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 * This class is the RESTful controller that handles users operations
 * <ul>
 * <li>Registration</li>
 * <li>Login</li>
 * <li>Logout</li>
 * <li>Password recovery</li>
 * <li>Profile modification</li>
 * <li>Deletion/Deactivation</li>
 * </ul>
 * <p/>
 * User: tartur
 * Date: 12/6/12
 * Time: 12:35 AM
 */
@Path("/users")
@Produces("application/json")
public class UserResource {
    @Inject
    private DataService dataService;

    @GET
//    @Path("/{username}")
    public User get() {
        return dataService.findUserByUsername("tartur");
    }
}
