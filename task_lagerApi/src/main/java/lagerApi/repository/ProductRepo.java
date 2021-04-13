package lagerApi.repository;

import org.springframework.data.repository.CrudRepository;

import lagerApi.entity.Product;

public interface ProductRepo extends CrudRepository<Product, String> {

}
