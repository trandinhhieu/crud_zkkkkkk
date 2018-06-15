package com.hieu.zzzzzzzzzzzzz.services;

import java.util.List;

import com.hieu.zzzzzzzzzzzzz.entity.Category;
import com.hieu.zzzzzzzzzzzzz.entity.Product;

public interface MyService {

	Product addProduct(Product Product);

	List<Product> getProducts();

	void deleteProduct(Product Product);
	
	List<Product> search(String keyword);
	
	List<Product> searchById(Category cat);
	
	Product editProduct(Product product);
	
}
