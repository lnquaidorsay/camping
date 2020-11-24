package com.camping.service;

import java.util.List;

import com.camping.entities.Category;

public interface CategoryService {
	void ajoutCatgery(Category category);

	void modifierCategory(Category category);

	List<Category> listCategory();
}
