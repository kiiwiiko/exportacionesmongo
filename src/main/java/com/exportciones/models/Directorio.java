package com.exportciones.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.ArrayList;
import java.util.List;

public class Directorio {
    @Id
    private String id;
    @Indexed(name="codigo",unique = true)
    private String nombre;
    @DBRef
    private List<ProductoExportado> listaProductosExportados = new ArrayList<>();

    public Directorio() {
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

    public List<ProductoExportado> getListaProductosExportados() {
        return listaProductosExportados;
    }

    public void setListaProductosExportados(List<ProductoExportado> listaProductosExportados) {
        this.listaProductosExportados = listaProductosExportados;
    }

    public void addProductoExportado(ProductoExportado productoExportado) {
        listaProductosExportados.add(productoExportado);
    }
}
