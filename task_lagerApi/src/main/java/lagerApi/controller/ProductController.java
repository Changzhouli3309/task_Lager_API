package lagerApi.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lagerApi.entity.Product;
import lagerApi.model.request.ProductRequestModel;
import lagerApi.service.ProductService;

@RestController
@RequestMapping("products") // http://localhost:8080/products
public class ProductController {
	private ProductService pro_ser;

	@Autowired
	public ProductController(ProductService pro_ser) {
		this.pro_ser = pro_ser;
	}

	@GetMapping("/all")
	public ResponseEntity<List<Product>> getProducts() {
		List<Product> products = pro_ser.findAll();
		return products.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(products);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Product> getProduct(@PathVariable String id) {
		Product p = pro_ser.getProduct(id);
		if (p != null) {
			return ResponseEntity.ok(p);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping
	public ResponseEntity<Product> createProduct(@Validated @RequestBody ProductRequestModel newPro) {
		if (newPro == null || newPro.getCost() < 0) {
			return ResponseEntity.badRequest().build();
		} else {
			Product saved = pro_ser.createProduct(new Product(newPro.getName(), newPro.getCost(), newPro.getCategory()));

			return ResponseEntity.status(HttpStatus.CREATED).body(saved);
		}

	}

	@PutMapping("/{id}")
	public ResponseEntity<Product> updateProduct(@PathVariable String id, @RequestBody ProductRequestModel updated) {
		if (updated == null || updated == null || updated.getCost() < 0) {
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity
				.ok(pro_ser.updateProduct(id, updated.getName(), updated.getCost(), updated.getCategory()));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Product> deleteProduct(@PathVariable String id) {
		return pro_ser.deleteProduct(id) ? ResponseEntity.ok().build() : ResponseEntity.badRequest().build();
	}

}
