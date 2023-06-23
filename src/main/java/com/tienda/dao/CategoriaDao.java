package com.tienda.dao;

import com.tienda.domain.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
//to use JPA, findAll, findByID...
public interface CategoriaDao extends JpaRepository <Categoria,Long> {
    
}