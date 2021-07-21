package com.shekinah.store.serviceproduct;


import com.shekinah.store.serviceproduct.entity.Categoria;
import com.shekinah.store.serviceproduct.entity.Producto;
import com.shekinah.store.serviceproduct.repository.CategoriaRepository;
import com.shekinah.store.serviceproduct.repository.ProductRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.Date;
import java.util.List;

@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
public class ProductRepositoryMockTest {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Test
    public void whenFindByCategory_thenReturnListProduct (){

        /*Categoria categoria = Categoria.builder()
                .nombre("Primera Cat")
                .build();

        categoriaRepository.save(categoria);*/

        Producto producto1 = Producto.builder()
                .nombre("Celular j7")
                .descrip("Samsung SMJ700M")
                .estado(true)
                .categoria(Categoria.builder().id(1L).build())
                //.stock(Double.parseDouble("10"))
                .stock(10)
                .precio(15)
                .fechaCreacion(new Date()).build();

        productRepository.save(producto1);

        Categoria ca2 = new Categoria();
        ca2.setId(3L);

        List<Producto> listProductos = productRepository.findByCategoria(producto1.getCategoria());
        //List<Producto> listProductos = productRepository.findByCategoria(ca2);
        Assertions.assertThat(listProductos.size()).isEqualTo(3);

    }
}
