package br.com.lab.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.lab.entity.Category;

public interface CategoryRepository extends CrudRepository<Category, Long> {

}
