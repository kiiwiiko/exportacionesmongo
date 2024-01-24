package com.exportciones.models;

import org.aspectj.apache.bcel.classfile.Module;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Exportar {
    @Id
    private String id;
    private String nombre;
    @Indexed(name="codigo",unique = true)
    private String codigo;
    private String destino;
    private int cantidad;

    public Exportar(String nombre, String codigo, int cantidad, String destino) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.cantidad = cantidad;
        this.destino = destino;
    }

    public Exportar() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
