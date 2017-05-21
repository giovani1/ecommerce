package com.ensa.service;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;

import com.ensa.models.Categorie;
/**
 * Session Bean implementation class CategorieService
 */
@Stateless
@LocalBean
public class CategorieService {
	
	@PersistenceContext( unitName = "ec1" )
	EntityManager em;
	
	public Categorie add(@NotNull Categorie categorie) {
		em.persist(categorie);
		em.flush();
		return categorie;
	}

	public Categorie update(@NotNull Categorie categorie){
		return em.merge(categorie);
	}

	public Categorie find(@NotNull int id) {
        return em.find(Categorie.class, id);
    }
	
	public Categorie find(@NotNull String name) {
        TypedQuery<Categorie> typedQuery = em.createNamedQuery(Categorie.FIND_BY_NAME, Categorie.class);
        typedQuery.setParameter("pname", name);
        return typedQuery.getSingleResult();
    }
	
	public List<Categorie> findAll() {
        TypedQuery<Categorie> typedQuery = em.createNamedQuery(Categorie.FIND_ALL, Categorie.class);
        return typedQuery.getResultList();
    }

	public void remove(Categorie find) {
		em.remove(em.merge(find));
	}

	public List<Categorie> findByParent(int i) {
		TypedQuery<Categorie> typedQuery = em.createNamedQuery(Categorie.FIND_By_PARENT, Categorie.class);
		typedQuery.setParameter("pid", i);
        return typedQuery.getResultList();
	}
}
