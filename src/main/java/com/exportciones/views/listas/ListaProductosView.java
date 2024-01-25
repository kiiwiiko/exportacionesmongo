package com.exportciones.views.listas;
import com.exportciones.models.ProductoImportado;
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

@PageTitle("Lista Productos")
@Route(value = "lista-productos", layout = MainLayout.class)
@Uses(Icon.class)
public class ListaProductosView extends Composite<VerticalLayout> {


    private ProductoImportadoService productoImportadoService;

    public ListaProductosView(ProductoImportadoService productoImportadoService) {

        this.productoImportadoService=productoImportadoService;

        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");

        Grid<ProductoImportado> grid = new Grid<>(ProductoImportado.class, false);
        grid.addColumn(ProductoImportado::getNombre).setHeader("Nombre").setAutoWidth(true);
        grid.addColumn(ProductoImportado::getCodigo).setHeader("Codigo").setAutoWidth(true);
        grid.addColumn(ProductoImportado::getOrigen).setHeader("Origen").setAutoWidth(true);
        grid.addColumn(ProductoImportado::getCantidad).setHeader("Cantidad").setAutoWidth(true);

        List<ProductoImportado> productosImportado = productoImportadoService.listaProductos();
        grid.setItems(productosImportado);
        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER);


        getContent().add(grid);

    }
}
