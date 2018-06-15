package com.hieu.zzzzzzzzzzzzz;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.GlobalCommand;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Window;

import com.hieu.zzzzzzzzzzzzz.entity.Category;
import com.hieu.zzzzzzzzzzzzz.entity.Product;
import com.hieu.zzzzzzzzzzzzz.services.CategoryService;
import com.hieu.zzzzzzzzzzzzz.services.MyService;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class MyViewModel {

	@WireVariable
	private MyService myService;

	@WireVariable
	private CategoryService catService;

	private ListModelList<Product> productListModel;
	private ListModelList<Category> categoryListModel;

	private Product selectedProduct;
	private Category selectedCategory;

	private Product product;

	private String keyword;

	@Init
	public void init() {

		List<Category> listCategory = catService.getCategorys();
		categoryListModel = new ListModelList<Category>(listCategory);
		List<Product> listProduct = myService.getProducts();
		productListModel = new ListModelList<Product>(listProduct);
		setSelectedCategory(categoryListModel.get(0));

		product = new Product();
	}
	
	@Command
	public void showAddProduct(@BindingParam("vm") Object vm) {
		Map<String, Object> map = new HashMap<String, Object>();
		Executions.createComponents("add.zul", null, map);
		BindUtils.postNotifyChange(null, null, vm, "productListModel");
	}

	@Command
	public void addProduct(@BindingParam("close") Window wd) {
		myService.addProduct(product);
		product = new Product();
		wd.detach();
		Clients.showNotification("Add Success","info", null, "top_center", 4100);
		BindUtils.postGlobalCommand(null, null, "refresh", null);
	}

	@GlobalCommand
	@NotifyChange("*")
	public void refresh() {
		List<Product> producttList = myService.getProducts();
		productListModel = new ListModelList<Product>(producttList);
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Product getSelectedProduct() {
		return selectedProduct;
	}

	public ListModelList<Category> getCategoryListModel() {
		return categoryListModel;
	}

	public void setCategoryListModel(ListModelList<Category> categoryListModel) {
		this.categoryListModel = categoryListModel;
	}

	public void setSelectedProduct(Product selectedProduct) {
		this.selectedProduct = selectedProduct;
	}

	public Category getSelectedCategory() {
		return selectedCategory;
	}

	public void setSelectedCategory(Category selectedCategory) {
		this.selectedCategory = selectedCategory;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public ListModelList<Product> getProductListModel() {
		return productListModel;
	}

	public void setProductListModel(ListModelList<Product> productListModel) {
		this.productListModel = productListModel;
	}

	@Command
	public void deleteProduct(@BindingParam("product") Product product) {
		myService.deleteProduct(product);
		productListModel.remove(product);
	}
	
	@GlobalCommand
	public void showEditProduct(@BindingParam("product") Product product) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("product", product);
		map.put("listCat", categoryListModel);
		Executions.createComponents("edit.zul", null, map);
		
	}
	
	@GlobalCommand
	@NotifyChange("*")
	public void editProduct(@BindingParam("product")Product p, @BindingParam("close") Window wd) {
		myService.editProduct(p);
		wd.detach();
	}

	@Command
	@NotifyChange("productListModel")
	public void searchById() {
		List<Product> result = myService.searchById(selectedCategory);
		productListModel = new ListModelList<Product>(result);
	}

	@Command
	@NotifyChange("productListModel")
	public void search() {
		List<Product> listProduct = myService.search(keyword);
		productListModel = new ListModelList<Product>(listProduct);
	}

	@Command
	@NotifyChange("productListModel")
	public void searchAll() {
		List<Product> listProduct = myService.getProducts();
		productListModel = new ListModelList<Product>(listProduct);
	}
}
