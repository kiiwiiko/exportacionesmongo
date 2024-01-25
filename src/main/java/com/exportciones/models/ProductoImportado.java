package com.exportciones.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class ProductoImportado extends Producto {
    private String origen;




    public ProductoImportado(String nombre, String codigo, String origen, int cantidad) {
        super(nombre, codigo, cantidad);

        this.origen = origen;
    }

    public ProductoImportado() {
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }
}
