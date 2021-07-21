package com.shekinah.store.serviceproduct.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shekinah.store.serviceproduct.Exceptions.ErrorMessages;
import com.shekinah.store.serviceproduct.entity.Categoria;
import com.shekinah.store.serviceproduct.entity.Producto;
import com.shekinah.store.serviceproduct.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/*
@RestController para indicar que implementamos un servicio rest
@RequestMapping para indicar la URI es decir la puerta de entrada del
servicio web, no nombrarlos con nombres de metodos
y debe ser el nombre la entidad terminado en plural


@AutoWired, relacion de dependencia es como una instanciacion
 */

@RestController
@RequestMapping(value = "/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/listproducts")
    public ResponseEntity <List<Producto>> listProduct(){
        List<Producto> products = productService.listAllProduct();

        if(products.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(products);
    }

    @GetMapping( "/products_cat")
    public ResponseEntity <List<Producto>> listProductByCategory(@RequestParam(name = "idCategory", required = false) Long idCategory){
       List<Producto> resultProducts = new ArrayList<>();

        if(idCategory == null){
            resultProducts = productService.listAllProduct();
            if(resultProducts.isEmpty()){
                return ResponseEntity.noContent().build();
            }
       }else {
            resultProducts = productService.findByCategoria(Categoria.builder().id(idCategory).build());
            if(resultProducts.isEmpty()){
                return ResponseEntity.notFound().build();
            }
        }
        return ResponseEntity.ok(resultProducts);
    }


   /* @GetMapping("/getById")
    public ResponseEntity<Producto> getProductById(@RequestParam(value = "idProduct") Long idProduct){
        Producto producto = productService.getProductById(idProduct);


        return  ResponseEntity.ok(producto);
    }*/

    @GetMapping(value = "/{id}")
    public ResponseEntity<Producto> getProductById(@PathVariable("id") Long idProduct){
        Producto producto = productService.getProductById(idProduct);
        if(producto==null){
            return ResponseEntity.noContent().build();
        }

        return  ResponseEntity.ok(producto);
    }

    @PostMapping
    public ResponseEntity<Producto> insertProduct(@Valid @RequestBody Producto producto, BindingResult resultValidation){
        if(resultValidation.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.formatMessages(resultValidation));
        }

        Producto createdProduct = productService.createProduct(producto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
    }

    @PutMapping
    public ResponseEntity<Producto> updateProduct(@RequestBody Producto producto){
        Producto updatedProduct = productService.updateProduct(producto);
        return ResponseEntity.ok(updatedProduct);
    }


    @DeleteMapping(value = "/{id}")
    public String deleteProduct(@PathVariable("id") Long idProduct){
        Producto producto = productService.getProductById(idProduct);
        if(producto == null) {
            //return ResponseEntity.notFound().build();
            return "Producto no encontrado";
        }
        productService.deleteProduct(producto.getId());

        return "Eliminado correctamente";
    }

    @GetMapping("/{id}/stock")
    public ResponseEntity<Producto> updateStock (@PathVariable(value = "id") Long id , @RequestParam(name = "quantity") Integer quantity){
        Producto producto = productService.getProductById(id);
        if(producto == null){
            return  ResponseEntity.notFound().build();
        }

        producto = productService.updateStock(id, quantity);
        return ResponseEntity.ok(producto);
    }


    /***
     * Metodo para dar formato al mensaje de error
     */

    public String formatMessages(BindingResult result){
        List<Map<String, String>> errorList= result.getFieldErrors()
                .stream()
                .map(err -> {
                    Map<String,String> error  = new HashMap<>();
                    error.put(err.getField(), err.getDefaultMessage());
                    return  error;
                }).collect(Collectors.toList());

        ErrorMessages errorMessages = ErrorMessages.builder()
                .codeMessage("01")
                .listMessages(errorList)
                .build();

        //Usams Jaxson para convertir estos mensajes en un json

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonContent = "";
        try {
            jsonContent = objectMapper.writeValueAsString(errorMessages);
        }catch (JsonProcessingException e){
            e.printStackTrace();
        }

        return  jsonContent;
    }

}
