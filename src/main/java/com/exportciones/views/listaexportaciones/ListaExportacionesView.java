package com.exportciones.views.listaexportaciones;

import com.exportciones.models.ProductoExportado;
import com.exportciones.services.ProductoExportadoService;
import com.exportciones.views.MainLayout;
import com.exportciones.views.nuevaexportacion.NuevaExportacionView;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility.Gap;

import java.util.List;

@PageTitle("Lista Exportaciones")
@Route(value = "lista-exportaciones", layout = MainLayout.class)
@Uses(Icon.class)
public class ListaExportacionesView extends Composite<VerticalLayout> {

    private ProductoExportadoService productoExportadoService;

    public ListaExportacionesView(ProductoExportadoService productoExportadoService) {
        this.productoExportadoService = productoExportadoService;

        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");

        Grid<ProductoExportado> grid = new Grid<>(ProductoExportado.class, false);
        grid.addColumn(ProductoExportado::getNombre).setHeader("Nombre").setAutoWidth(true);
        grid.addColumn(ProductoExportado::getCodigo).setHeader("Codigo").setAutoWidth(true);
        grid.addColumn(ProductoExportado::getDestino).setHeader("Destino").setAutoWidth(true);
        grid.addColumn(ProductoExportado::getCantidad).setHeader("Cantidad").setAutoWidth(true);

        List<ProductoExportado> productoExportados = productoExportadoService.listaProductosExportados();
        grid.setItems(productoExportados);

        getContent().add(grid);
    }
}