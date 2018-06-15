package com.hieu.zzzzzzzzzzzzz.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import com.hieu.zzzzzzzzzzzzz.entity.Category;
import com.hieu.zzzzzzzzzzzzz.services.CategoryService;

@Service("catService")
@Scope(value = "singleton", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryDao categoryDao;

//	public Category addCategory(Category Category) {
//		return dao.save(Category);
//	}
//
	public List<Category> getCategorys() {
		return categoryDao.queryAll();
	}
//
//	public void deleteCategory(Category Category) {
//		dao.delete(Category);
//	}
}
