package com.codejava.restecommercetest.dao;

import com.codejava.restecommercetest.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

@Repository
@CrossOrigin(origins = "http://localhost:4200")
public interface CategoryRepository extends JpaRepository<ProductCategory, Long> {
}
