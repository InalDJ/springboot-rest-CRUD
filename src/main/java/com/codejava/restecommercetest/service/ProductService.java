package com.codejava.restecommercetest.service;

import com.codejava.restecommercetest.dao.ProductRepository;
import com.codejava.restecommercetest.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import org.springframework.stereotype.Service;


@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Page<Product> listAll(int page, int size){
        return productRepository.findAll(PageRequest.of(page,size));
    }
}

