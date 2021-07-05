package com.example.shopapi.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.shopapi.model.Products;

public class ProductsCustomRepositoryImpl implements ProductsCustomRepository {

	@Autowired
	private EntityManager entityManager;

	public List<Products> findProductsByCatagory(String name, String color, Long size) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Products> criteriaQuery = criteriaBuilder.createQuery(Products.class);
		Root<Products> productsRoot = criteriaQuery.from(Products.class);
		List<Predicate> predicates = new ArrayList();
		System.out.println(size);
		if (!name.equals("null")) {
			predicates.add(criteriaBuilder.like(productsRoot.get("name"), "%" + name + "%"));
		}
		if (!color.equals("null")) {
			predicates.add(criteriaBuilder.like(productsRoot.get("color"), "%" + color + "%"));
		}
		if (size > -1) {
			predicates.add(criteriaBuilder.equal(productsRoot.get("size"), size));
		}
		Predicate predicate = criteriaBuilder.and(predicates.toArray(new Predicate[0]));
		criteriaQuery.where(predicate);
		TypedQuery<Products> typeQuery = entityManager.createQuery(criteriaQuery);
		List<Products> result = typeQuery.getResultList();
		return result;
	}
}
