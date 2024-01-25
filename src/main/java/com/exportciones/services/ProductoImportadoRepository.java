package com.exportciones.services;

import com.exportciones.models.Producto;
import com.exportciones.models.ProductoImportado;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductoImportadoRepository extends MongoRepository<ProductoImportado,String> {
    public ProductoImportado findByCodigo(String codigo);
}
