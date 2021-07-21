package com.shekinah.store.serviceproduct.service;

import com.shekinah.store.serviceproduct.entity.Categoria;
import com.shekinah.store.serviceproduct.entity.Producto;

import java.util.List;

public interface ProductService {
    public List<Producto> listAllProduct();
    public Producto getProductById(Long idProduct);
    public Producto createProduct(Producto producto);
    public Producto updateProduct(Producto producto);
    public Producto deleteProduct(Long idProduct);
    public List<Producto> findByCategoria (Categoria categoria);
    public Producto updateStock(Long idProduct, Integer quantity);

}
