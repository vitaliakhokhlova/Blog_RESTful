package com.vita.khokhlova.services;

import java.util.List;
import java.util.stream.Collectors;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.vita.khokhlova.repositories.*;
import com.vita.khokhlova.entities.*;

@Path("/user")
public class UserCRUD {

    private UserRepository userRepository = new UserRepository();

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public User login(User user) {
        return userRepository.login(user);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> getAll() {
        return userRepository.getAll();
    }

    @GET
    @Path("/{id}/mypage")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Post> getMyPage(@PathParam("id") int id) {
        return userRepository.getMyPage(id)
                .stream()
                .filter(post -> (post != null))
                .filter(post -> (post.getParent() != null))
                .filter(post -> (post.getParent().getId() == 0))
                .collect(Collectors.toList());
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public User getById(@PathParam("id") int id) {
        return userRepository.getById(id);
    }

    @GET
    @Path("/{id}/mywall")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Post> getMyWall(@PathParam("id") int userid){
        UserRepository userRepository = new UserRepository();
        List<User> friends = userRepository.getFriends(userid);
        List<Post> wall = getMyPage(userid);
    	for (User f : friends) {
            wall.addAll(getMyPage(f.getId()));
        }
        return wall;
    }

	@GET
	@Path("/{id}/friendlist")
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> getFriends(@PathParam("id") int id) {
		return userRepository.getFriends(id);
	}


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public User create(User user) {
        userRepository.create(user);
        return user;
    }

    @PUT
    @Path("/{userid}")
    @Consumes(MediaType.APPLICATION_JSON)
    public User addFriend(@PathParam("userid") int userid, User friend) {
        return userRepository.addFriend(userid, friend);
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") int id) {
        Response response;
        User user = userRepository.getById(id);
        if (user == null) {
            response = Response.ok("entity with id=" + id + " does not exist").build();
        } else {
            userRepository.delete(id);
            response = Response.ok("entity " + user + " is deleted").build();
        }
        return response;
    }


    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public User update(User user) {
        User bexists = userRepository.getById(user.getId());
        String s = bexists.toString();
        userRepository.update(user);
        return user	;
    }


}
