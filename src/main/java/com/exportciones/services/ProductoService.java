package com.exportciones.services;

import com.exportciones.models.Producto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductoService {
    private ProductoRepository productoRepository;

    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public List<Producto> listaProductos() {
        List<Producto> listaProductos = new ArrayList<>();
        try {
            listaProductos = productoRepository.findAll();
        }catch (Exception e) {
            System.out.println("ERROR: No se puedo coenctar a la base de datos.");
        }
        return listaProductos;
    }

    public Producto obtenerPorCodigo(String codigo) {
        Producto producto = null;
        try {
            producto = productoRepository.findByCodigo(codigo);
        }catch (Exception e) {
            System.out.println("ERROR: No se encontro un producto con ese codigo.");
        }
        return producto;
    }

    public void agregarProducto(Producto producto) {
        try {
            productoRepository.save(producto);
        }catch (Exception e) {
            System.out.println(("ERROR: No se prudo guardar el producto."));
        }
    }

    public void borrarProducto(String codigo) {
        try {
            Producto producto = productoRepository.findByCodigo(codigo);
            productoRepository.delete(producto);
        }catch (Exception e) {
            System.out.println("ERROR: No se pudo encontrar el producto.");
        }
    }

    public void editarProducto(String codigo, Producto producto) {
        try {
            Producto productoEditar = productoRepository.findByCodigo(codigo);
            productoEditar.setNombre(producto.getNombre());
            productoRepository.save(productoEditar);
        }catch (Exception e) {
            System.out.println("ERROR: No se puede encontrar el producto.");
        }
    }
}
