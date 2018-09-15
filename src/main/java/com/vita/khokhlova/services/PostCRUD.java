package com.vita.khokhlova.services;

import com.vita.khokhlova.entities.Post;
import com.vita.khokhlova.repositories.PostRepository;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/post")
public class PostCRUD {

    private PostRepository postRepository = new PostRepository();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Post> getAll() {
        return postRepository.getAll();
    }



    @GET
    @Path("/{postid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Post getById(@PathParam("postid") int postid) {
        return postRepository.getById(postid);
    }

    @GET
    @Path("/{postid}/comments")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Post> getComments(@PathParam("postid") int postid)
    {
        return postRepository.getComments(postid);
    }


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Post create(Post post) {
        postRepository.create(post);
        return post;
    }

    @DELETE
    @Path("/{postid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletePost(@PathParam("postid") int postid) {
        Response response;
        Post post = postRepository.getById(postid);
        if (post == null) {
            response = Response.ok("entity with id=" + postid + " does not exist").build();
        } else {
            postRepository.delete(postid);
            response = Response.ok("entity " + post + " is deleted").build();
        }
        return response;
    }


    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Post updatePostByPut(Post post) {
        Post bexists = postRepository.getById(post.getId());
        String s = bexists.toString();
        postRepository.update(post);
        return post	;
    }


}
