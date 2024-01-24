package com.exportciones.services;

import com.exportciones.models.ProductoExportado;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductoExportadoRepository extends MongoRepository<ProductoExportado,String> {
    public ProductoExportado findByNombre(String nombre);
    public ProductoExportado findByCodigo(String codigo);
}
