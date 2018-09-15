package com.vita.khokhlova.repositories;

import com.vita.khokhlova.EntityManagerFactorySingleton;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import com.vita.khokhlova.entities.*;
import java.util.List;

public class PostRepository {

    private EntityManager em = EntityManagerFactorySingleton.getEntityManager();
    private String queryStart ="select b from Post b where b.user.id=";

    public Post getById(int postid) {
        return  em.find(Post.class,postid);
    }


    public List<Post> getComments(int postid){
        return em.createQuery("select b from Post b where b.parent.id=" + postid + " ORDER by b.id DESC").getResultList();
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
            tx.commit();
        }
        catch (Exception e) {
            tx.rollback();
        }
    }

}
