package com.vita.khokhlova.repositories;

import com.vita.khokhlova.EntityManagerFactorySingleton;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import com.vita.khokhlova.entities.*;
import java.util.List;

public class PostRepository {

    private EntityManager em = EntityManagerFactorySingleton.getEntityManager();
    private String queryStart ="select b from Post b";

    public List<Post> getAll(){
        return em.createQuery(queryStart).getResultList();
    }

    public Post getById(int postid) {
        return  em.find(Post.class,postid);
    }


    public List<Post> getComments(int postid){
        return em.find(Post.class,postid).getComments();
    }


    public void delete(int id) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Post b = em.find(Post.class, id);
        if (b != null) {
            em.remove(b);
        }
        tx.commit();
    }

    public void create(Post post) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(post);
            em.refresh(post);
            tx.commit();
        }
        catch (Exception e) {
            tx.rollback();
        }
    }

    public void update(Post post) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
                em.persist(em.merge(post));
                em.refresh(post);
            tx.commit();
        }
        catch (Exception e) {
            tx.rollback();
        }
    }

}
