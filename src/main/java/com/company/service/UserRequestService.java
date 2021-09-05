package com.company.service;


import com.company.dao.Impl.UserDaoImpl;
import com.company.dao.Impl.UserRequestDaoImpl;
import com.company.model.User;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/request")
public class UserRequestService {

    UserDaoImpl userData = new UserDaoImpl();
    UserRequestDaoImpl requestData = new UserRequestDaoImpl();
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response getAllRequest(){
        return Response.ok(requestData.getAllRequest()).build();
    }
    @GET
    @Path("/delete")
    @Produces({MediaType.APPLICATION_JSON})
    public Response delAllRequest(){
        requestData.deletAllRequest();
        return Response.ok().entity("Кэш запросов успешно очищен").build();
    }

    @POST
    @Path("/id/{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response getById(@PathParam("id") Long id){
        System.out.println("Bag Exist");
        User user = userData.getById(id);
        requestData.setNewRequset(user);
        return Response.ok(user).build();
    }
    @POST
    @Path("/login/{login}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response getById(@PathParam("login") String login){
        User user = userData.getByLogin(login);
        requestData.setNewRequset(user);
        return Response.ok(user).build();
    }

    @POST
    @Path("/birthday/{data}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response getById(@PathParam("data") int data){
        List<User> users = userData.getByDateBirth(data);
        return Response.ok(users).build();
    }
    @POST
    @Path("/gender/{gender}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response getById(@PathParam("gender") Boolean gender){
        List<User> users = userData.getByGender(gender);
        return Response.ok().entity(users).build();
    }
}
