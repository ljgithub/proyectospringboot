package com.shekinah.store.serviceproduct.repository;

import com.shekinah.store.serviceproduct.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    public List<Categoria> findByNombre(Categoria categoria);
}
