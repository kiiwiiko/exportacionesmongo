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
            // Verificar si la información necesaria está presente
            if (esInformacionValida(productoImportado)) {
                String codigoProducto = productoImportado.getCodigo();
                String nombreProducto = productoImportado.getNombre();

                // Buscar el producto existente por nombre y código
                ProductoImportado productoExistente = productoImportadoRepository.findByNombreAndCodigo(nombreProducto, codigoProducto);

                if (productoExistente != null) {
                    // Sumar la cantidad al producto existente
                    productoExistente.setCantidad(productoExistente.getCantidad() + productoImportado.getCantidad());
                    productoImportadoRepository.save(productoExistente);
                    System.out.println("Cantidad de Producto Importado actualizada correctamente.");
                } else {
                    // Verificar si el nombre ya existe en la base de datos
                    ProductoImportado productoPorNombre = productoImportadoRepository.findByNombre(nombreProducto);
                    if (productoPorNombre != null) {
                        System.out.println("ERROR: Ya existe un Producto Importado con ese nombre y código. No se pudo agregar el nuevo Producto Importado.");
                    } else {
                        // Guardar el nuevo producto importado
                        productoImportadoRepository.save(productoImportado);
                        System.out.println("Producto Importado agregado correctamente.");
                    }
                }
            } else {
                System.out.println("ERROR: Información incompleta. Por favor, ingrese todos los campos obligatorios.");
            }
        } catch (Exception e) {
            System.out.println("ERROR: No se pudo guardar el producto.");
        }
    }

    private boolean esInformacionValida(ProductoImportado productoImportado) {
        // Verificar que la información necesaria esté presente
        return productoImportado != null
                && productoImportado.getNombre() != null
                && !productoImportado.getNombre().isEmpty()
                && productoImportado.getCodigo() != null
                && !productoImportado.getCodigo().isEmpty()
                && productoImportado.getCantidad() > 0;  // O cualquier validación adicional que necesites
    }
    private boolean esNombreUnico(String nombre) {
        // Verificar si el nombre ya existe en la base de datos
        return productoImportadoRepository.findByNombre(nombre) == null;
    }


    private boolean existeProductoImportadoConCodigo(String codigo) {
        return productoImportadoRepository.findByCodigo(codigo) != null;
    }

    public void editarProductoImportado(String codigo, ProductoImportado productoImportado) {
        try {
            ProductoImportado productoEditar = productoImportadoRepository.findByCodigo(codigo);
            productoEditar.setNombre(productoImportado.getNombre());
            productoEditar.setCantidad(productoImportado.getCantidad());
            productoImportadoRepository.save(productoEditar);
        } catch (Exception e) {
            System.out.println("ERROR: No se puede encontrar o editar el producto importado.");
        }
    }

    public void borrarProductoImportado(ProductoImportado productoImportado) {
        try {
            productoImportadoRepository.delete(productoImportado);
            System.out.println("Producto Importado eliminado correctamente.");
        } catch (Exception e) {
            System.out.println("ERROR: No se pudo encontrar o borrar el producto importado.");
        }
    }
}
