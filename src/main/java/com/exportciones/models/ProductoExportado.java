package com.exportciones.models;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class ProductoExportado extends Producto {
    private String destino;
    private double costo;




    public ProductoExportado(String nombre, String codigo, String destino, int cantidad, double costo) {
        super(nombre, codigo, cantidad);
        this.destino = destino;
        this.costo = costo;
    }

    public ProductoExportado() {
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }
}