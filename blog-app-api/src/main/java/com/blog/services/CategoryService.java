package com.blog.services;

import java.util.List;

import com.blog.payloads.CategoryDto;

public interface CategoryService {
	
	public CategoryDto createCategory(CategoryDto categoryDto);
	
	public CategoryDto updateCategory(CategoryDto categoryDto,Integer id);
	
	public void deleteCategory(Integer categoryId);
	
	public CategoryDto getCategory(Integer categoryId);
	
	List<CategoryDto> getCategories();

}
