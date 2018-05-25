package com.emmanuel.ejb;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PreDestroy;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;

import com.emmanuel.entity.Compute;
import com.emmanuel.entity.User;
import com.emmanuel.utils.AuthenticationUtils;

@Stateless
public class UserEJB {

	@PersistenceContext(unitName="assignmentPU")
	private EntityManager em;
	
	public User createUser(User user) {
		try {
			user.setPassword(AuthenticationUtils.encodeSHA256(user.getPassword()));
		} catch (Exception e) {
			Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, e);
			e.printStackTrace();
		}
		
		em.persist(user);

		return user;
		
	}
	
	public Compute createBasic(Compute basicCalc) {
		em.persist(basicCalc);

		return basicCalc;
		
	}
	
	public List<Compute> findComputeByUsername(String username) {
		TypedQuery<Compute> query = em.createNamedQuery("Compute.findComputeByUsername", Compute.class);
		query.setParameter("username", username);
		return query.getResultList();
	}
	
	public User findUserByUsername(String username) {
		TypedQuery<User> query = em.createNamedQuery("User.findByUsername", User.class);
		query.setParameter("username", username);
		User user = null;
		try {
			user = query.getSingleResult();
		} catch (Exception e) {
			// getSingleResult throws NoResultException in case there is no user in DB
			// ignore exception and return NULL for user instead
		}
		return user;
	}
	
	public User findUserByPassword(String password) {
		TypedQuery<User> query = em.createNamedQuery("User.findByPassword", User.class);
		query.setParameter("password", password);
		User user = null;
		try {
			user = query.getSingleResult();
		} catch (Exception e) {
			// getSingleResult throws NoResultException in case there is no user in DB
			// ignore exception and return NULL for user instead
		}
		return user;
	}
	
	
	public List<Compute> findAll() {
		CriteriaQuery cq =
		         em.getCriteriaBuilder().createQuery();
		      cq.select(cq.from(Compute.class));
		return em.createQuery(cq).getResultList();
	}
	
	
	
	public User findUser(String username,String password) {
		TypedQuery<User> query = em.createNamedQuery("User.findUser", User.class);
		query.setParameter("username", username);
		query.setParameter("password", password);
		User user = null;
		try {
			user = query.getSingleResult();
		} catch (Exception e) {
			// getSingleResult throws NoResultException in case there is no user in DB
			// ignore exception and return NULL for user instead
		}
		return user;
	}
	
	
	@PreDestroy
	public void destruct()
	{
	    em.close();
	}

}
