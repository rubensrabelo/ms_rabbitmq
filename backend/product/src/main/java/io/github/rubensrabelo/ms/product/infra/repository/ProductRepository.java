package io.github.rubensrabelo.ms.product.infra.repository;

import io.github.rubensrabelo.ms.product.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
