package sk.cassoviacode.app.persistance;

import org.springframework.data.jpa.repository.JpaRepository;
import sk.cassoviacode.app.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
