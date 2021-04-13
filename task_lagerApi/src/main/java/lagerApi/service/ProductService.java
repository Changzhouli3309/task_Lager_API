package lagerApi.service;

import java.util.List;

import lagerApi.entity.Product;

public interface ProductService {

	List<Product> findAll();

	Product getProduct(String id);

	Product createProduct(Product p);

	Product updateProduct(String id, String name, int cost, String category);

	boolean deleteProduct(String id);
}
