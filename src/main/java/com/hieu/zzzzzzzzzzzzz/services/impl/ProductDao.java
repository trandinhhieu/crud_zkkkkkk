package com.hieu.zzzzzzzzzzzzz.services.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.hieu.zzzzzzzzzzzzz.entity.Category;
import com.hieu.zzzzzzzzzzzzz.entity.Product;

@Repository
public class ProductDao {

	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	private EntityManager em;

	@Transactional(readOnly = true)
	public List<Product> queryAll() {
		return em.createQuery("SELECT p FROM Product p Order by p.id DESC", Product.class).getResultList();
	}

	@Transactional(readOnly = true)
	public Product get(Integer id) {
		return em.find(Product.class, id);
	}

	@Transactional
	public Product save(Product Product) {
		if (Product.getId() != null) {
			em.persist(Product);
		} else {
			em.merge(Product);
		}
		return Product;
	}

	@Transactional
	public void delete(Product Product) {
		Product r = get(Product.getId());
		if (r != null) {
			em.remove(r);
		}
	}

	@Transactional(readOnly = true)
	public List<Product> search(String keyword) {
		if (keyword == null || "".equals(keyword)) {
			return queryAll();
		}
		return em.createQuery("SELECT p FROM Product p WHERE p.name LIKE '%" + keyword + "%'", Product.class)
				.getResultList();
	}

	@Transactional(readOnly = true)
	public List<Product> searchById(Category cat) {
		return em.createQuery("SELECT p FROM Product p WHERE p.category.id =" + cat.getId(), Product.class)
				.getResultList();
	}

}
