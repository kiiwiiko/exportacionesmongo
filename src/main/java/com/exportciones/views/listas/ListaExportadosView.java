package com.exportciones.views.listas;

import com.exportciones.models.ProductoExportado;
import com.exportciones.models.ProductoImportado;
import com.exportciones.services.ProductoExportadoService;
import com.exportciones.services.ProductoImportadoService;
import com.exportciones.views.MainLayout;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.util.List;

@PageTitle("Lista Exportados")
@Route(value = "lista-exportados", layout = MainLayout.class)
@Uses(Icon.class)
public class ListaExportadosView extends Composite<VerticalLayout> {


    private ProductoExportadoService productoExportadoService;
    private ProductoImportadoService productoImportadoService;

    public ListaExportadosView(ProductoExportadoService productoExportadoService) {

        this.productoExportadoService=productoExportadoService;

        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");

        Grid<ProductoExportado> grid = new Grid<>(ProductoExportado.class, false);
        grid.addColumn(ProductoExportado::getNombre).setHeader("Nombre").setAutoWidth(true);
        grid.addColumn(ProductoExportado::getCodigo).setHeader("Codigo").setAutoWidth(true);
        grid.addColumn(ProductoExportado::getDestino).setHeader("Destino").setAutoWidth(true);
        grid.addColumn(ProductoExportado::getCantidad).setHeader("Cantidad").setAutoWidth(true);
        grid.addColumn(ProductoExportado::getCosto).setHeader("Precio").setAutoWidth(true);


        grid.addComponentColumn(productoExportado -> {
            Button deleteButton = new Button(new Icon(VaadinIcon.TRASH));
            deleteButton.addClickListener(e -> {
                productoExportadoService.eliminarExpo(productoExportado);
                grid.setItems(productoExportadoService.listaProductos());
            });
            return deleteButton;
        }).setHeader("Eliminar").setAutoWidth(true);

        // Obtener la lista de productos importados y establecer en la tabla
        grid.setItems(productoExportadoService.listaProductos());

        List<ProductoExportado> productosExportado = productoExportadoService.listaProductos();
        grid.setItems(productosExportado);
        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER);


        getContent().add(grid);

    }
}
