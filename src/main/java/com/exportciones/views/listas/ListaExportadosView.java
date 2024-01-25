package com.exportciones.views.listas;

import com.exportciones.models.ProductoExportado;
import com.exportciones.models.ProductoImportado;
import com.exportciones.services.ProductoExportadoService;
import com.exportciones.services.ProductoImportadoService;
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

@PageTitle("Lista Exportados")
@Route(value = "lista-exportados", layout = MainLayout.class)
@Uses(Icon.class)
public class ListaExportadosView extends Composite<VerticalLayout> {


    private ProductoExportadoService productoExportadoService;

    public ListaExportadosView(ProductoExportadoService productoExportadoService) {

        this.productoExportadoService=productoExportadoService;

        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");

        Grid<ProductoExportado> grid = new Grid<>(ProductoExportado.class, false);
        grid.addColumn(ProductoExportado::getNombre).setHeader("Nombre").setAutoWidth(true);
        grid.addColumn(ProductoExportado::getCodigo).setHeader("Codigo").setAutoWidth(true);
        grid.addColumn(ProductoExportado::getDestino).setHeader("Destino").setAutoWidth(true);
        grid.addColumn(ProductoExportado::getCantidad).setHeader("Cantidad").setAutoWidth(true);

        List<ProductoExportado> productosExportado = productoExportadoService.listaProductos();
        grid.setItems(productosExportado);
        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER);


        getContent().add(grid);

    }
}
