package com.camping.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.camping.entities.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

	List<Category> findByNameContaining(String name);

	@Query("SELECT c FROM Category c WHERE c.id = ?1")
	Category chercherCategory(Long id);
}
