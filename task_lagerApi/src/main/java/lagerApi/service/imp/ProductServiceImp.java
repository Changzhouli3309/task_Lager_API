package lagerApi.service.imp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lagerApi.entity.Product;
import lagerApi.service.ProductService;
import lagerApi.repository.ProductRepo;

@Service
public class ProductServiceImp implements ProductService{
	
	private ProductRepo proRepo;
	
	@Autowired
	public ProductServiceImp(ProductRepo proRepo) {
		this.proRepo = proRepo;
	}

	@Override
	public List<Product> findAll() {
		return (List<Product>) proRepo.findAll();
	}

	@Override
	public Product getProduct(String id) {
		return proRepo.findById(id).orElse(null);
	}

	@Override
	public Product updateProduct(String id, String name, int cost, String category) {
		Product p = getProduct(id);
		p.setName(name);
		p.setCost(cost);
		p.setCategory(category);
		return proRepo.save(p);
	}

	@Override
	public boolean deleteProduct(String id) {
		Optional<Product> res = proRepo.findById(id);
		if(res.isPresent()) {
			proRepo.delete(res.get());
			return true;
		}else {
			return false;
		}
	}

	@Override
	public Product createProduct(Product p) {
		return proRepo.save(p);
	}

}
