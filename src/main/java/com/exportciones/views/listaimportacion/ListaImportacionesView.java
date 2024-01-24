package com.exportciones.views.listaimportacion;

import com.exportciones.models.ProductoExportado;
import com.exportciones.models.ProductoImportado;
import com.exportciones.services.ProductoExportadoService;
import com.exportciones.services.ProductoImportadoService;
import com.exportciones.views.MainLayout;
import com.exportciones.views.nuevaexportacion.NuevaExportacionView;
import com.exportciones.views.nuevaimportacion.NuevaImportacionView;
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
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.theme.lumo.LumoUtility.Gap;

import java.util.List;

@PageTitle("Lista Importaciones")
@Route(value = "lista-importaciones", layout = MainLayout.class)
@Uses(Icon.class)
public class ListaImportacionesView extends Composite<VerticalLayout> {

    private ProductoImportadoService productoImportadoService;

    public ListaImportacionesView(ProductoImportadoService productoImportadoService) {

        this.productoImportadoService = productoImportadoService;

        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");

        Grid<ProductoImportado> grid = new Grid<>(ProductoImportado.class, false);
        grid.addColumn(ProductoImportado::getNombre).setHeader("Nombre").setAutoWidth(true);
        grid.addColumn(ProductoImportado::getCodigo).setHeader("Codigo").setAutoWidth(true);
        grid.addColumn(ProductoImportado::getCantidad).setHeader("Cantidad").setAutoWidth(true);
        grid.addColumn(ProductoImportado::getOrigen).setHeader("Origen").setAutoWidth(true);

        grid.addColumn(
                new ComponentRenderer<>(productoImportado -> {
                    Button botonBorrar = new Button();
                    botonBorrar.addThemeVariants(ButtonVariant.LUMO_ERROR);
                    botonBorrar.addClickListener(e -> {

                        productoImportadoService.borrarProductoImportado(productoImportado.getCodigo());

                        grid.getDataProvider().refreshAll();
                    });
                    botonBorrar.setIcon(new Icon(VaadinIcon.TRASH));

                    Button botonEditar = new Button();
                    botonEditar.addThemeVariants(ButtonVariant.LUMO_SUCCESS);
                    botonEditar.addClickListener(e -> {
                        botonEditar.getUI().ifPresent(ui ->
                                ui.navigate(NuevaImportacionView.class,productoImportado.getCodigo()));
                    });
                    botonEditar.setIcon(new Icon(VaadinIcon.EDIT));

                    HorizontalLayout buttons = new HorizontalLayout(botonBorrar,botonEditar);
                    return buttons;
                })).setHeader("Manage").setAutoWidth(true);

        List<ProductoImportado> productoImportados = productoImportadoService.listaProductosImportados();
        grid.setItems(productoImportados);

        getContent().add(grid);

    }
}
