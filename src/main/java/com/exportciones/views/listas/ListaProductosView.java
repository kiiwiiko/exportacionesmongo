package com.exportciones.views.listas;

import com.exportciones.models.Producto;
import com.exportciones.services.ProductoService;
import com.exportciones.views.MainLayout;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;


import java.util.List;

@PageTitle("Lista Productos")
@Route(value = "lista-productos", layout = MainLayout.class)
@Uses(Icon.class)
public class ListaProductosView extends Composite<VerticalLayout> {


    private ProductoService productoService;

    public ListaProductosView(ProductoService productoService) {

        this.productoService=productoService;

        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");

        Grid<Producto> grid = new Grid<>(Producto.class, false);
        grid.addColumn(Producto::getNombre).setHeader("Nombre").setAutoWidth(true);
        grid.addColumn(Producto::getCodigo).setHeader("Codigo").setAutoWidth(true);
        grid.addColumn(Producto::getOrigen).setHeader("Origen").setAutoWidth(true);
        grid.addColumn(Producto::getCantidad).setHeader("Cantidad").setAutoWidth(true);

        List<Producto> productos = productoService.listaProductos();
        grid.setItems(productos);
        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER);


        getContent().add(grid);

    }
}
