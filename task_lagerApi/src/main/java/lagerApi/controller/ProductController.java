package lagerApi.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lagerApi.entity.Product;
import lagerApi.model.request.ProductRequestModel;
import lagerApi.service.ProductService;
import lagerApi.service.exception.*;

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
		if (products.isEmpty()) {
			throw new NoContentException("204 No Content");
		} else {
			return ResponseEntity.ok(products);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<Product> getProduct(@PathVariable String id) {
		Product p = pro_ser.getProduct(id);
		if (p != null) {
			return ResponseEntity.ok(p);
		} else {
			throw new NotFoundException("404 Not Found");
		}
	}

	@PostMapping
	public ResponseEntity<Product> createProduct(@Validated @RequestBody ProductRequestModel newPro) {
		if (newPro == null || newPro.getCost() < 0) {
			throw new BadRequestException("400 Bad Request");
		} else {
			Product saved = pro_ser
					.createProduct(new Product(newPro.getName(), newPro.getCost(), newPro.getCategory()));

			return ResponseEntity.status(HttpStatus.CREATED).body(saved);
		}

	}

	@PutMapping("/{id}")
	public ResponseEntity<Product> updateProduct(@PathVariable String id, @RequestBody ProductRequestModel updated) {
		if (id == null || updated == null || updated.getCost() < 0) {
			throw new BadRequestException("400 Bad Request");
		}
		return ResponseEntity
				.ok(pro_ser.updateProduct(id, updated.getName(), updated.getCost(), updated.getCategory()));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Product> deleteProduct(@PathVariable String id) {
		if (pro_ser.deleteProduct(id)) {
			return ResponseEntity.ok().build();
		} else {
			throw new BadRequestException("400 Bad Request");
		}
	}

}
