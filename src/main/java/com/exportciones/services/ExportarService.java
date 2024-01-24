package com.exportciones.services;

import com.exportciones.models.Exportar;
import com.exportciones.models.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExportarService {

    private ExportarRepository exportarRepository;
    private ProductoService productoService;
    private ProductoRepository productoRepository;

    @Autowired
    public ExportarService(ProductoRepository productoRepository,ExportarRepository exportarRepository, ProductoService productoService) {
        this.exportarRepository = exportarRepository;
        this.productoService = productoService;
    }

    public List<Exportar> listaExportaciones() {
        List<Producto> listaProductos = new ArrayList<>();
        try {
            listaProductos = productoRepository.findAll();
        }catch (Exception e) {
            System.out.println("ERROR: No se puedo coenctar a la base de datos.");
        }
        return listaExportaciones();
    }

    public void agregarExportacion(Exportar exportar) {
        String codigoProducto = exportar.getCodigo();
        Producto producto = productoService.obtenerPorCodigo(codigoProducto);

        if (producto != null) {
            int cantidadExportada = exportar.getCantidad();
            producto.setCantidad(producto.getCantidad() - cantidadExportada);
            productoService.editarProducto(codigoProducto, producto);
            exportarRepository.save(producto);
        } else {
            throw new RuntimeException("No se puede exportar el producto. Verifique el código del producto.");
        }
    }

    public void borrarExportacion(String codigo) {
        Exportar exportacion = exportarRepository.findByCodigo(codigo);

        if (exportacion != null) {
            // Sumar la cantidad exportada al producto
            String codigoProducto = exportacion.getCodigo();
            Producto producto = productoService.obtenerPorCodigo(codigoProducto);
            int cantidadExportada = exportacion.getCantidad();
            producto.setCantidad(producto.getCantidad() + cantidadExportada);
            productoService.editarProducto(codigoProducto, producto);
        } else {
            throw new RuntimeException("No se pudo encontrar la exportación.");
        }
    }
}