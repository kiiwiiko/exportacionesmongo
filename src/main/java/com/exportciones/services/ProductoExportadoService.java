package com.exportciones.services;

import com.exportciones.models.ProductoExportado;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductoExportadoService {
    private ProductoExportadoRepository productoExportadoRepository;

    public ProductoExportadoService(ProductoExportadoRepository productoExportadoRepository) {
        this.productoExportadoRepository = productoExportadoRepository;
    }

    public List<ProductoExportado> listaProductosExportados() {
        List<ProductoExportado> listaProductosExportados = new ArrayList<>();
        try {
            listaProductosExportados = productoExportadoRepository.findAll();
        }catch (Exception e) {
            System.out.println("ERROR: No se puedo coenctar a la base de datos.");
        }
        return listaProductosExportados;
    }

    public ProductoExportado obtenerPorCodigo(String codigo) {
        ProductoExportado productoExportado = null;
        try {
            productoExportado = productoExportadoRepository.findByCodigo(codigo);
        }catch (Exception e) {
            System.out.println("ERROR: No se encontro un contacto con ese codigo.");
        }
        return productoExportado;
    }

    public void agregarExportacion(ProductoExportado productoExportado) {
        try {
            productoExportadoRepository.save(productoExportado);
        }catch (Exception e) {
            System.out.println(("ERROR: No se prudo guardar la exportacion."));
        }
    }

    public void borrarProductoExportado(String codigo) {
        try {
            ProductoExportado productoExportado = productoExportadoRepository.findByCodigo(codigo);
            productoExportadoRepository.delete(productoExportado);
        }catch (Exception e) {
            System.out.println("ERROR: No se pudo encontrar el producto.");
        }
    }

    public void editarProductoExportado(String codigo, ProductoExportado productoExportado) {
        try {
            ProductoExportado productoEditar = productoExportadoRepository.findByCodigo(codigo);
            productoEditar.setNombre(productoExportado.getNombre());
            productoEditar.setDestino(productoExportado.getDestino());
            productoExportadoRepository.save(productoEditar);
        }catch (Exception e) {
            System.out.println("ERROR: No se puede encontrar el producto.");
        }
    }
}
