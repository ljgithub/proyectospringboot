package com.shekinah.store.serviceproduct.service;

import com.shekinah.store.serviceproduct.entity.Categoria;
import com.shekinah.store.serviceproduct.entity.Producto;
import com.shekinah.store.serviceproduct.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{


    private final ProductRepository productRepository;

    @Override
    public List<Producto> listAllProduct() {
        return productRepository.findAll();
    }

    @Override
    public Producto getProductById(Long idProduct) {
        return productRepository.findById(idProduct).orElse(null);
    }

    @Override
    public Producto createProduct(Producto producto) {
        producto.setEstado(true);
        producto.setFechaCreacion(new Date());
        return productRepository.save(producto);
    }

    @Override
    public Producto updateProduct(Producto producto) {
        Producto productDB = getProductById(producto.getId());
        if(productDB == null){
            return null;
        }else {
            productDB = producto;
            productRepository.save(productDB);
        }

        return productDB;
    }

    @Override
    public Producto deleteProduct(Long idProduct) {
        Producto productDB = getProductById(idProduct);
        if(productDB == null){
            return null;
        }
        productDB.setEstado(false);

        return productRepository.save(productDB);

    }

    @Override
    public List<Producto> findByCategoria(Categoria categoria) {
        return productRepository.findByCategoria(categoria);
    }

    @Override
    public Producto updateStock(Long idProduct, Integer quantity) {
        Producto productDB = getProductById(idProduct);
        if(productDB == null){
            return null;
        }
        Integer stockProduct =  productDB.getStock();
        stockProduct+=quantity;

        productDB.setStock(stockProduct);

        return productRepository.save(productDB);
    }
}
