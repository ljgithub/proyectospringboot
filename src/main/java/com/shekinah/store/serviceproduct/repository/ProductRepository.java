package com.shekinah.store.serviceproduct.repository;

import com.shekinah.store.serviceproduct.entity.Categoria;
import com.shekinah.store.serviceproduct.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/*
*  COnsultar  porque en esta interfaz se usa extends osea hereda
*  de otra interfaz
*  Ya que extends se sobreentiende que se usa solamente
*  para heredar de otra clase
* */

public interface ProductRepository extends JpaRepository<Producto, Long> {

    public List<Producto> findByCategoria(Categoria categoria);

}
