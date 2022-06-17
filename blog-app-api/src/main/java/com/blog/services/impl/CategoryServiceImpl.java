package com.blog.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.entities.Category;
import com.blog.exceptions.ResourceNotFoundException;
import com.blog.payloads.CategoryDto;
import com.blog.repositories.CategoryRepo;
import com.blog.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{
	
	@Autowired
	private CategoryRepo categoryRepo;

	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		Category category = dtoToEntity(categoryDto);
		Category savedCategory = this.categoryRepo.save(category);
		return entityToDto(savedCategory);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto,Integer categoryId) {
		
		Category cat = this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category ","Category id ",categoryId));
		cat.setCategoryTitle(categoryDto.getCategoryTitle());
		cat.setCategoryDescription(categoryDto.getCategoryDescription());
		
		Category updatedCategory = this.categoryRepo.save(cat);
		
		return entityToDto(updatedCategory);
	}

	@Override
	public void deleteCategory(Integer categoryId) {
		Category cat = this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category ","Category id ",categoryId));
		this.categoryRepo.delete(cat);
		
	}

	@Override
	public CategoryDto getCategory(Integer categoryId) {
		Category cat = this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category ","Category id ",categoryId));
		return entityToDto(cat);
	}

	@Override
	public List<CategoryDto> getCategories() {
		List<Category> categories = this.categoryRepo.findAll();
		List<CategoryDto> listDto = new ArrayList<>();
		for(Category category:categories) {
			CategoryDto dto = new CategoryDto();
			dto=entityToDto(category);
			listDto.add(dto);
		}
		return listDto;
	}
	
	
	public Category dtoToEntity(CategoryDto dto) {
		Category category = new Category();
		BeanUtils.copyProperties(dto,category);
		return category;
	}
	
	public CategoryDto entityToDto(Category category) {
		CategoryDto categoryDto = new CategoryDto();
		BeanUtils.copyProperties(category,categoryDto);
		return categoryDto;
	}

}
