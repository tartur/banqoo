
package com.tartur.banqoo.rest;

import com.tartur.banqoo.model.User;

import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/** Example resource class hosted at the URI path "/myresource"
 */
@Path("/myresource")
@Stateless
public class MyResource {
    
    /** Method processing HTTP GET requests, producing "text/plain" MIME media
     * type.
     * @return String that will be send back as a response of type "text/plain".
     */
    @GET 
    @Produces("text/plain")
    public String getIt() {
        System.out.println("**************** I get it **************************");
        return "Hi there!";
    }

    /** Method processing HTTP GET requests, producing "text/plain" MIME media
     * type.
     * @return String that will be send back as a response of type "text/plain".
     */
    @GET
    @Path("/user")
    @Produces("application/json")
    public User getUser()    {
        User u = new User("tartur", "coucou", "tartur@gmail.com");
        return u;
    }
}
