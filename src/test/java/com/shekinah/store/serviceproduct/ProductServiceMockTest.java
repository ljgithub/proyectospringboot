package com.shekinah.store.serviceproduct;

import com.shekinah.store.serviceproduct.entity.Categoria;
import com.shekinah.store.serviceproduct.entity.Producto;
import com.shekinah.store.serviceproduct.repository.ProductRepository;
import com.shekinah.store.serviceproduct.service.ProductService;
import com.shekinah.store.serviceproduct.service.ProductServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class ProductServiceMockTest {

    @Mock
    private ProductRepository productRepository;

    private ProductService productService;


    @BeforeEach
    public void setup(){
        MockitoAnnotations.initMocks(this);
        productService = new ProductServiceImpl(productRepository);

        Producto product02 = Producto.builder()
                .id(1L)
                .nombre("Dell 7577")
                .descrip("")
                .estado(true)
                .categoria(Categoria.builder().id(3L).build())
                //.stock(Double.parseDouble("10"))
                .stock(5)
                .precio(1500)
                .fechaCreacion(new Date()).build();

        Mockito.when(productRepository.findById(1L))
                .thenReturn(Optional.of(product02));

        Mockito.when(productRepository.save(product02))
                .thenReturn(product02);
    }

    @Test
    public void whenValidGetId_ThenReturnProduct(){
        Producto found = productService.getProductById(1L);
        Assertions.assertThat(found.getNombre()).isEqualTo("HP Omen");
    }

    @Test
    public void whenValidUpdateStock_ThenReturnNewStock(){
        Producto producto03 = productService.updateStock(1L, 5);
        Assertions.assertThat(producto03.getStock()).isEqualTo(9);

    }

    @Test
    public void whenUpdatePrice_ThenReturnUpdatedProduct(){
        Producto product04 = productService.getProductById(1L);
        product04.setPrecio(75);

        Assertions.assertThat(product04.getPrecio()).isEqualTo(1500);
    }

    @Test
    public void whenProductIsDeleted_ThenReturnUpdatedStatus(){
        Producto product05 = productService.getProductById(1L);
        productService.deleteProduct(product05.getId());

        Assertions.assertThat(product05.isEstado()).isEqualTo(false);
    }

}
