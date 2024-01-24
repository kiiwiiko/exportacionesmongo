package com.exportciones.services;

import com.exportciones.models.Producto;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductoRepository extends MongoRepository<Producto,String> {
    public Producto findByCodigo(String codigo);
}
