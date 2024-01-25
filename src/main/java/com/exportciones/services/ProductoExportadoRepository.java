package com.exportciones.services;

import com.exportciones.models.ProductoExportado;
import com.exportciones.models.ProductoImportado;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductoExportadoRepository extends MongoRepository<ProductoExportado,String> {
    public ProductoExportado findByCodigo(String codigo);
}
