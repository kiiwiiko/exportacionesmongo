package com.exportciones.models;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class ProductoImportado extends Producto {
    private String origen;
    public ProductoImportado(String id, String nombre, String codigo, int cantidad, String origen){
        super(nombre,codigo, cantidad);
        this.origen = origen;
    }

    public ProductoImportado(String nombre, String codigo, String destino, int cantidad){
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String destino) {
        this.origen = destino;
    }
}
