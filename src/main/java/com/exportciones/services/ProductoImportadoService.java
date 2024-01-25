package com.exportciones.services;

import com.exportciones.models.Producto;
import com.exportciones.models.ProductoExportado;
import com.exportciones.models.ProductoImportado;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductoImportadoService {
    private ProductoImportadoRepository productoImportadoRepository;

    public ProductoImportadoService(ProductoImportadoRepository productoImportadoRepository) {
        this.productoImportadoRepository = productoImportadoRepository;
    }

    public List<ProductoImportado> listaProductos() {
        List<ProductoImportado> listaProductos = new ArrayList<>();
        try {
            listaProductos = productoImportadoRepository.findAll();
        }catch (Exception e) {
            System.out.println("ERROR: No se puedo coenctar a la base de datos.");
        }
        return listaProductos;
    }

    public ProductoImportado obtenerPorCodigo(String codigo) {
        ProductoImportado productoImportado = null;
        try {
            productoImportado = productoImportadoRepository.findByCodigo(codigo);
        }catch (Exception e) {
            System.out.println("ERROR: No se encontro un producto con ese codigo.");
        }
        return productoImportado;
    }

    public void agregarProducto(ProductoImportado productoImportado) {
        try {
            String codigoProducto = productoImportado.getCodigo();

            // Verificar si ya existe un producto importado con el mismo código
            if (!existeProductoImportadoConCodigo(codigoProducto)) {
                // Guardar el nuevo producto importado
                productoImportadoRepository.save(productoImportado);
                System.out.println("Producto Importado agregado correctamente.");
            } else {
                System.out.println("ERROR: Ya existe un Producto Importado con ese código. No se pudo agregar el nuevo Producto Importado.");
            }
        } catch (Exception e) {
            System.out.println("ERROR: No se pudo guardar el producto.");
        }
    }

    private boolean existeProductoImportadoConCodigo(String codigo) {
        return productoImportadoRepository.findByCodigo(codigo) != null;
    }

    public void borrarProducto(String codigo) {
        try {
            ProductoImportado productoImportado =
                    productoImportadoRepository.findByCodigo(codigo);
            productoImportadoRepository.delete(productoImportado);
        }catch (Exception e) {
            System.out.println("ERROR: No se pudo encontrar el producto.");
        }
    }

    public void editarProducto(String codigo, ProductoImportado productoImportado) {
        try {
            ProductoImportado productoEditar = productoImportadoRepository.findByCodigo(codigo);
            productoEditar.setNombre(productoImportado.getNombre());
            productoImportadoRepository.save(productoEditar);
        }catch (Exception e) {
            System.out.println("ERROR: No se puede encontrar el producto.");
        }
    }
}
