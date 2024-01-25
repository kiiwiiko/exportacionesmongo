package com.exportciones.services;

import com.exportciones.models.Producto;
import com.exportciones.models.ProductoExportado;
import com.exportciones.models.ProductoImportado;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductoExportadoService {
    private ProductoExportadoRepository productoExportadoRepository;
    private ProductoImportadoRepository productoImportadoRepository;

    public ProductoExportadoService(ProductoExportadoRepository productoExportadoRepository,
                                    ProductoImportadoRepository productoImportadoRepository) {
        this.productoExportadoRepository = productoExportadoRepository;
        this.productoImportadoRepository = productoImportadoRepository;
    }
    public List<ProductoExportado> listaProductos() {
        List<ProductoExportado> listaProductos = new ArrayList<>();
        try {
            listaProductos = productoExportadoRepository.findAll();
        } catch (Exception e) {
            System.out.println("ERROR: No se pudo conectar a la base de datos.");
        }
        return listaProductos;
    }

    public ProductoExportado obtenerPorCodigo(String codigo) {
        ProductoExportado productoExportado = null;
        try {
            productoExportado = productoExportadoRepository.findByCodigo(codigo);
        } catch (Exception e) {
            System.out.println("ERROR: No se encontró un producto con ese código.");
        }
        return productoExportado;
    }

    public void agregarExpo(ProductoExportado productoExportado) {
        try {
            String codigoProducto = productoExportado.getCodigo();

            // Verificar si existe un producto importado con el código antes de agregar
            if (existeProductoImportadoConCodigo(codigoProducto)) {
                // Obtener el producto importado
                ProductoImportado productoImportado = productoImportadoRepository.findByCodigo(codigoProducto);

                // Verificar si hay suficientes productos importados para exportar
                if (productoImportado.getCantidad() >= productoExportado.getCantidad()) {
                    // Restar la cantidad exportada de los productos importados
                    restarCantidadProductosImportados(codigoProducto, productoExportado.getCantidad());

                    // Guardar el producto exportado
                    productoExportadoRepository.save(productoExportado);
                    System.out.println("Producto Exportado agregado correctamente.");
                } else {
                    System.out.println("ERROR: No hay suficientes productos importados para exportar la cantidad especificada.");
                }
            } else {
                System.out.println("ERROR: No existe un Producto Importado con ese código. No se pudo agregar el Producto Exportado.");
            }
        } catch (Exception e) {
            System.out.println("ERROR: No se pudo guardar el producto.");
        }
    }

    private void restarCantidadProductosImportados(String codigo, int cantidadExportada) {
        try {
            ProductoImportado productoImportado = productoImportadoRepository.findByCodigo(codigo);

            if (productoImportado != null) {
                int nuevaCantidad = productoImportado.getCantidad() - cantidadExportada;

                // Verificar si la cantidad llega a cero y eliminar el producto importado
                if (nuevaCantidad <= 0) {
                    productoImportadoRepository.delete(productoImportado);
                    System.out.println("Producto Importado eliminado debido a que la cantidad llegó a cero.");
                } else {
                    productoImportado.setCantidad(nuevaCantidad);
                    productoImportadoRepository.save(productoImportado);
                }
            } else {
                System.out.println("ERROR: No se encontró un Producto Importado con ese código.");
            }
        } catch (Exception e) {
            System.out.println("ERROR: No se pudo restar la cantidad de productos importados.");
        }
    }
    private boolean existeProductoImportadoConCodigo(String codigo) {
        return productoImportadoRepository.findByCodigo(codigo) != null;
    }
}