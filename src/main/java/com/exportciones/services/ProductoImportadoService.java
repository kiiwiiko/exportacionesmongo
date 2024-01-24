package com.exportciones.services;

import com.exportciones.models.ProductoExportado;
import com.exportciones.models.ProductoImportado;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class ProductoImportadoService {
    private ProductoImportadoRepository productoImportadoRepository;

    public ProductoImportadoService(ProductoImportadoRepository productoImportadoRepository){
        this.productoImportadoRepository=productoImportadoRepository;
    }

    public List<ProductoImportado> listaProductosImportados(){
        List<ProductoImportado> listaProductosImportados = new ArrayList<>();
        try{
            listaProductosImportados = productoImportadoRepository.findAll();
        }catch (Exception ex){
            System.out.println("No se pudo conectar con la base de datos");
        }
        return listaProductosImportados;
    }

    public ProductoImportado obtenerPorCodigo(String codigo){
        ProductoImportado productoImportado = null;
        try {
            productoImportado=productoImportadoRepository.findByCodigo(codigo);
        }catch (Exception ex){
            System.out.println("No se encontro un contacto con esa cedula");
        }
        return productoImportado;
    }

    public void agregarProductoImportado(ProductoImportado productoImportado){
        try{
            productoImportadoRepository.save(productoImportado);
        }catch (Exception ex){
            System.out.println("No se puede guardar el contacto");
        }
    }

    public void borrarProductoImportado(String codigo){
        try{
            ProductoImportado contacto = productoImportadoRepository.findByCodigo(codigo);
            productoImportadoRepository.delete(contacto);
        }catch (Exception ex){
            System.out.println("No se puede encontrar el contacto a borrar");
        }
    }

    public void editarProductoImportado(String codigo, ProductoImportado productoImportado){
        try{
            ProductoImportado productoEditar = productoImportadoRepository.findByCodigo(codigo);
            if(productoEditar!=null){
                productoEditar.setNombre(productoImportado.getNombre());
                productoEditar.setOrigen(productoImportado.getOrigen());
                productoEditar.setCantidad(productoImportado.getCantidad());
                productoImportadoRepository.save(productoEditar);
            }
        }catch (Exception ex){
            System.out.println("No se puede encontrar el contacto a ser editado");
        }
    }


}
