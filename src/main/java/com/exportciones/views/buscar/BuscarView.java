package com.exportciones.views.buscar;

import com.exportciones.models.ProductoImportado;
import com.exportciones.services.ProductoImportadoService;
import com.exportciones.views.MainLayout;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.theme.lumo.LumoUtility.Gap;

@PageTitle("Buscar")
@Route(value = "buscar", layout = MainLayout.class)
@Uses(Icon.class)
public class BuscarView extends Composite<VerticalLayout> {

    private final ProductoImportadoService productoImportadoService;

    public BuscarView(ProductoImportadoService productoImportadoService) {
        this.productoImportadoService = productoImportadoService;

        HorizontalLayout layoutRow = new HorizontalLayout();
        TextField textField = new TextField();
        Button buttonPrimary = new Button("Buscar");
        VerticalLayout layoutColumn2 = new VerticalLayout();

        getContent().setWidth("50%");
        getContent().getStyle().set("flex-grow", "1");

        layoutRow.setWidthFull();
        getContent().setFlexGrow(1.0, layoutRow);
        layoutRow.addClassName(Gap.MEDIUM);
        layoutRow.setWidth("100%");
        layoutRow.setHeight("min-content");

        textField.setLabel("Código");
        layoutRow.setAlignSelf(FlexComponent.Alignment.CENTER, textField);
        textField.getStyle().set("flex-grow", "1");

        buttonPrimary.setWidth("min-content");
        buttonPrimary.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buttonPrimary.addClickListener(e -> buscarProducto(textField.getValue()));

        layoutColumn2.setWidthFull();
        getContent().setFlexGrow(1.0, layoutColumn2);
        layoutColumn2.setWidth("100%");
        layoutColumn2.getStyle().set("flex-grow", "1");

        // Agregar el cuadro de búsqueda y el botón
        layoutRow.add(textField, buttonPrimary);
        getContent().add(layoutRow);

        // Agregar el layoutColumn2 para mostrar los resultados
        getContent().add(layoutColumn2);
    }

    private void buscarProducto(String codigo) {
        ProductoImportado producto = productoImportadoService.obtenerPorCodigo(codigo);

        if (producto != null) {
            // Mostrar información del producto debajo del cuadro de búsqueda
            mostrarInformacionProducto(producto);
        } else {
            Notification.show("Producto no encontrado", 3000, Notification.Position.MIDDLE);
        }
    }


    private void mostrarInformacionProducto(ProductoImportado producto) {
        // Crear un Grid para mostrar la información del producto
        Grid<ProductoImportado> grid = new Grid<>();
        grid.addColumn(ProductoImportado::getNombre).setHeader("Nombre");
        grid.addColumn(ProductoImportado::getCodigo).setHeader("Código");
        grid.addColumn(ProductoImportado::getCantidad).setHeader("Cantidad");

        grid.addComponentColumn(productoImportado -> {
            Button btnBorrar = new Button(new Icon(VaadinIcon.TRASH));
            btnBorrar.addClickListener(e -> {
                productoImportadoService.borrarProductoImportado(productoImportado);
                grid.setItems(productoImportadoService.listaProductos());

            });
            return btnBorrar;

        }).setHeader("Borrar").setAutoWidth(true);
        // Agregar el producto al Grid
        grid.setItems(producto);

        // Agregar componentes al diseño
        VerticalLayout layout = new VerticalLayout(grid);
        layout.setPadding(true);

        // Mostrar el cuadro de diálogo con la información del producto
        getContent().add(layout);
    }
}