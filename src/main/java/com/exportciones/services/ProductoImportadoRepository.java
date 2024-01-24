package com.exportciones.services;

import com.exportciones.models.ProductoExportado;
import com.exportciones.models.ProductoImportado;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductoImportadoRepository extends MongoRepository<ProductoImportado,String> {
    public ProductoImportado findByNombre(String nombre);
    public ProductoImportado findByCodigo(String codigo);
}