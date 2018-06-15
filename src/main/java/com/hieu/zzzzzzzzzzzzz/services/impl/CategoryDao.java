package com.hieu.zzzzzzzzzzzzz.services.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.hieu.zzzzzzzzzzzzz.entity.Category;

@Repository
public class CategoryDao {

	@PersistenceContext
	private EntityManager em;

	@Transactional(readOnly = true)
	public List<Category> queryAll() {
		return em.createQuery("SELECT c FROM Category c", Category.class).getResultList();
	}

//	@Transactional(readOnly = true)
//	public Category get(Integer id) {
//		return em.find(Category.class, id);
//	}
//
//	@Transactional
//	public Category save(Category Category) {
//		em.persist(Category);
//		em.flush();
//		return Category;
//	}
//
//	@Transactional
//	public void delete(Category Category) {
//		Category r = get(Category.getId());
//		if (r != null) {
//			em.remove(r);
//		}
//	}

}
