package com.exportciones.views.nuevo;

import com.exportciones.models.ProductoExportado;
import com.exportciones.models.ProductoImportado;
import com.exportciones.services.ProductoExportadoService;
import com.exportciones.services.ProductoImportadoService;
import com.exportciones.views.MainLayout;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.*;
import com.vaadin.flow.theme.lumo.LumoUtility.Gap;

@PageTitle("Nueva Exportacion")
@Route(value = "nueva-exportacion", layout = MainLayout.class)
@Uses(Icon.class)
public class NuevaExportacionView extends Composite<VerticalLayout> implements HasUrlParameter<String> {

    TextField tfNombre;
    TextField tfCodigo;
    ComboBox cbOrigen;
    TextField tfCantidad;
    Button btGuardar;
    Button btMenu;
    String selectedValue;
    String valorTexto;

    String codigo;
    double precioDestino;

    private ProductoExportadoService productoExportadoService;
    public NuevaExportacionView(ProductoExportadoService productoExportadoService)  {
        HorizontalLayout layoutRow = new HorizontalLayout();
        tfNombre = new TextField();
        tfCodigo = new TextField();
        HorizontalLayout layoutRow2 = new HorizontalLayout();
        cbOrigen = new ComboBox<>();
        tfCantidad = new TextField();
        Paragraph textMedium = new Paragraph();
        HorizontalLayout layoutRow3 = new HorizontalLayout();
        btGuardar = new Button();
        btMenu = new Button();
        VerticalLayout layoutColumn2 = new VerticalLayout();
        getContent().setWidth("100%");
        getContent().setHeight("500px");
        getContent().setJustifyContentMode(JustifyContentMode.START);
        getContent().setAlignItems(Alignment.CENTER);
        layoutRow.setWidthFull();
        getContent().setFlexGrow(1.0, layoutRow);
        layoutRow.addClassName(Gap.XLARGE);
        layoutRow.setWidth("100%");
        layoutRow.setHeight("min-content");
        layoutRow.setAlignItems(Alignment.CENTER);
        layoutRow.setJustifyContentMode(JustifyContentMode.CENTER);
        tfNombre.setLabel("Nombre del Producto");
        tfNombre.setWidth("300px");
        tfCodigo.setLabel("Codigo");
        tfCodigo.setWidth("300px");
        layoutRow2.setWidthFull();
        getContent().setFlexGrow(1.0, layoutRow2);
        layoutRow2.addClassName(Gap.XLARGE);
        layoutRow2.setWidth("100%");
        layoutRow2.setHeight("min-content");
        layoutRow2.setAlignItems(Alignment.CENTER);
        layoutRow2.setJustifyContentMode(JustifyContentMode.CENTER);
        cbOrigen.setLabel("Origen");
        cbOrigen.setWidth("300px");
        cbOrigen.setItems("EEUU", "Rusia", "China");
        cbOrigen.addValueChangeListener(event -> {
            selectedValue = event.getValue().toString();
            // Agregado: obtener el precio asociado al destino seleccionado
            precioDestino = obtenerPrecioDestino(selectedValue);
        });
        tfCantidad.setLabel("Cantidad");
        tfCantidad.setWidth("300px");
        textMedium.setText("");
        textMedium.setWidth("max-content");
        textMedium.getStyle().set("font-size", "var(--lumo-font-size-m)");
        btGuardar.setText("Exportar");
        btGuardar.setWidth("200px");
        btGuardar.setHeight("50px");
        btGuardar.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        btGuardar.addClickListener(e -> {
            valorTexto = tfCantidad.getValue();
            if (codigo != null) {
                String nombre = tfNombre.getValue();
                int cantidad = Integer.parseInt(valorTexto);
                double totalPrecio = precioDestino * cantidad; // Calcular el precio total
                ProductoExportado productoExportado = new ProductoExportado(nombre, null, selectedValue, cantidad, totalPrecio);
                productoExportadoService.agregarExpo(productoExportado);
            } else {
                String codigo1 = tfCodigo.getValue();
                String nombre = tfNombre.getValue();
                int cantidad = Integer.parseInt(valorTexto);
                double totalPrecio = precioDestino * cantidad; // Calcular el precio total
                ProductoExportado productoExportado = new ProductoExportado(nombre, codigo1, selectedValue, cantidad, totalPrecio);
                productoExportadoService.agregarExpo(productoExportado);
                btGuardar.getUI().ifPresent(ui -> ui.navigate("lista-exportados"));
            }
        });
        btMenu.setText("Menu");
        btMenu.setWidth("200px");
        btMenu.setHeight("50px");
        btMenu.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        btMenu.addClickListener(e -> {
            btMenu.getUI().ifPresent(ui ->
                    ui.navigate("menu"));
        });
        layoutRow3.setWidthFull();
        getContent().setFlexGrow(1.0, layoutRow2);
        layoutRow3.addClassName(Gap.XLARGE);
        layoutRow3.setWidth("100%");
        layoutRow3.setHeight("min-content");
        layoutRow3.setAlignItems(Alignment.CENTER);
        layoutRow3.setJustifyContentMode(JustifyContentMode.CENTER);
        getContent().setFlexGrow(1.0, layoutColumn2);
        layoutColumn2.setWidth("100%");
        layoutColumn2.getStyle().set("flex-grow", "1");
        layoutColumn2.setJustifyContentMode(JustifyContentMode.START);
        layoutColumn2.setAlignItems(Alignment.CENTER);
        getContent().add(layoutRow);
        layoutRow.add(tfNombre);
        layoutRow.add(tfCodigo);
        getContent().add(layoutRow2);
        layoutRow2.add(cbOrigen);
        layoutRow2.add(tfCantidad);
        getContent().add(textMedium);
        getContent().add(layoutRow3);
        layoutRow3.add(btGuardar);
        layoutRow3.add(btMenu);
        getContent().add(layoutColumn2);
    }

    @Override
    public void setParameter(BeforeEvent beforeEvent, @OptionalParameter String codigo) {
        this.codigo = codigo;
        if(codigo != null){
            tfCodigo.setEnabled(false);
            ProductoExportado productoBuscado =  productoExportadoService.obtenerPorCodigo(codigo);
            tfNombre.setValue(productoBuscado.getNombre());
            tfCodigo.setValue(productoBuscado.getCodigo());
        }else {
            tfCodigo.setEnabled(true);
        }
    }

    private double obtenerPrecioDestino(String destino) {
        switch (destino) {
            case "EEUU":
                return 10.0;
            case "Rusia":
                return 15.0;
            case "China":
                return 12.0;
            default:
                return 0.0; // Precio predeterminado si no se encuentra el destino
        }
    }

}
