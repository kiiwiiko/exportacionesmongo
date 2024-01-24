package com.exportciones.services;

import com.exportciones.models.Exportar;
import com.exportciones.models.Producto;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ExportarRepository extends MongoRepository<Producto,String> {
    public Exportar findByCodigo(String codigo);

}
