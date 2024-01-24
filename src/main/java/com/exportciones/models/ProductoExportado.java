package com.exportciones.models;

public class ProductoExportado extends Producto {
    private String destino;

    public ProductoExportado(String id, String nombre, String codigo, int cantidad, String Destino){
        super(nombre,codigo, cantidad);
        this.destino = destino;
    }

    public ProductoExportado(String nombre, String codigo, String destino, int cantidad){
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

}


