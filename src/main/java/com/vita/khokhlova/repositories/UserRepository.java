package com.vita.khokhlova.repositories;

import com.vita.khokhlova.EntityManagerFactorySingleton;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import com.vita.khokhlova.entities.*;
import java.util.List;

public class UserRepository {

    private EntityManager em = EntityManagerFactorySingleton.getEntityManager();

    public List<User> getAll(){
        return em.createQuery("select u from User u").getResultList();
    }

    public User getById(int id) {
        return  em.find(User.class,id);
    }

    public List<User> getFriends(int id) {
    	User user = em.find(User.class,id);
        return user.getFriends();
    }


    public void create(User user) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(user);
            tx.commit();
        }
        catch (Exception e) {
            tx.rollback();
        }
    }

    public void delete(int id) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        User b = em.find(User.class, id);
        if (b != null) {
            em.remove(b);
        }
        tx.commit();
    }


    public void update(User user) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(em.merge(user));
            tx.commit();
        }
        catch (Exception e) {
            tx.rollback();
        }
    }

    public User addFriend(int id, User friend) {
        User user = em.find(User.class,id);
        System.out.println(id);
        List<User> friendList = user.getFriends();
        System.out.println(friendList);
        friendList.add(friend);
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(em.merge(user));
            tx.commit();
        }
        catch (Exception e) {
            tx.rollback();
        }
        return user;
    }

    public User login(User user){
        List<User> list = em.createQuery("select p from User p where upper(p.email)  like '" + user.getEmail().toUpperCase() + "' and p.password like '"+user.getPassword()+"'").getResultList();
        return list.get(0);
    }

    public List<Post> getMyPage(int id) {

        User user = em.find(User.class,id);
        List<Post> myPage= user.getPosts();

        return myPage;
    }
}