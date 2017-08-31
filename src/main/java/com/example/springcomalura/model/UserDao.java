/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.springcomalura.model;

import com.example.springcomalura.entity.User;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.constraints.AssertFalse.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author felly
 */
@Repository
@Transactional
public class UserDao {
    /**
    * Save the user in the database.
    */
   public void create(User user) {
     entityManager.persist(user);
     return;
   }

   /**
    * Delete the user from the database.
    */
   public void delete(User user) {
     if (entityManager.contains(user))
       entityManager.remove(user);
     else
       entityManager.remove(entityManager.merge(user));
     return;
   }

   /**
    * Return all the users stored in the database.
    */
   @SuppressWarnings("unchecked")
   public List getAll() {
     return (List) entityManager.createQuery("from User").getResultList();
   }

   /**
    * Return the user having the passed email.
    */
   public User getByEmail(String email) {
     return (User) entityManager.createQuery(
         "from User where email = :email")
         .setParameter("email", email)
         .getSingleResult();
   }

   /**
    * Return the user having the passed id.
    */
   public User getById(long id) {
     return entityManager.find(User.class, id);
   }

   /**
    * Update the passed user in the database.
    */
   public void update(User user) {
     entityManager.merge(user);
     return;
   }

   // Private fields
   //http://blog.netgloo.com/2014/10/06/spring-boot-data-access-with-jpa-hibernate-and-mysql/
   // An EntityManager will be automatically injected from entityManagerFactory
   // setup on DatabaseConfig class.
   @PersistenceContext
   private EntityManager entityManager;   
}
