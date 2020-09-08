package com.codejava.restecommercetest.controller;

import com.codejava.restecommercetest.dao.ProductRepository;
import com.codejava.restecommercetest.entity.Product;
import com.codejava.restecommercetest.exception.ResourceNotFoundException;
import com.codejava.restecommercetest.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class PageController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;


    @RequestMapping(
            value = "/products",
            params = { "page", "size" },
            method = RequestMethod.GET
    )
    public Page<Product> getAllProducts(
            @RequestParam("page") int page, @RequestParam("size") int size) throws ResourceNotFoundException {

        Page<Product> resultPage = productService.listAll(page, size);
        if (page > resultPage.getTotalPages()) {
            throw new ResourceNotFoundException("Requested page hasn't been found!");
        }
        return resultPage;
    }


    @RequestMapping(value = "/products",
                    method = RequestMethod.POST)
    public Product createProduct(@RequestBody Product product) {
        return productRepository.save(product);
    }

    @RequestMapping(value = "/products",
                    params = {"id"},
                    method = RequestMethod.GET)
    public ResponseEntity<Product> findProductById(@RequestParam("id") long productId) throws ResourceNotFoundException {
        Product product = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Couldn't find the product"));
        return ResponseEntity.ok().body(product);
    }

    @RequestMapping(value = "/products",
            params = {"id"},
            method = RequestMethod.PUT)
    public ResponseEntity<Product> updateProduct(@RequestParam("id") long productId, @RequestBody Product productDetails) throws ResourceNotFoundException {
        Product product = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Couldn't find the product"));

        product.setBrand(productDetails.getBrand());
        product.setName(productDetails.getName());
        product.setCategory(productDetails.getCategory());

        productRepository.save(product);
        return ResponseEntity.ok().body(product);
    }

    @RequestMapping(value = "/products",
            params = {"id"},
            method = RequestMethod.DELETE)
    public void deleteProduct(@RequestParam("id") long productId) throws ResourceNotFoundException {
        Product product = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Couldn't find the product"));
        productRepository.delete(product);
    }

}


