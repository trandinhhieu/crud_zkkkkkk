package com.hieu.zzzzzzzzzzzzz.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import com.hieu.zzzzzzzzzzzzz.entity.Category;
import com.hieu.zzzzzzzzzzzzz.entity.Product;
import com.hieu.zzzzzzzzzzzzz.services.MyService;

@Service("myService")
@Scope(value = "singleton", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class MyServiceImpl implements MyService {

	@Autowired
	ProductDao dao;

	public Product addProduct(Product Product) {
		return dao.save(Product);
	}

	public List<Product> getProducts() {
		return dao.queryAll();
	}

	public void deleteProduct(Product Product) {
		dao.delete(Product);
	}

	public List<Product> search(String keyword) {
		return dao.search(keyword);
	}
	
	public List<Product> searchById(Category cat) {
		return dao.searchById(cat);
	}

	public Product editProduct(Product product) {
		return dao.save(product);
	}

	
}
