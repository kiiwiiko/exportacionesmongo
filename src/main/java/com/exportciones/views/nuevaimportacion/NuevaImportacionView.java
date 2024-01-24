package com.exportciones.views.nuevaimportacion;

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
import java.util.ArrayList;
import java.util.List;

@PageTitle("Nueva Importacion")
@Route(value = "nueva-importacion", layout = MainLayout.class)
@Uses(Icon.class)
public class NuevaImportacionView extends Composite<VerticalLayout> implements HasUrlParameter<String> {

    TextField tfNombre;
    TextField tfCodigo;
    ComboBox cbOrigen;
    TextField tfCantidad;
    Button btGuardar;
    Button btMenu;

    String codigo;

    private ProductoImportadoService productoImportadoService;
    public NuevaImportacionView(ProductoImportadoService productoImportadoService)  {
        HorizontalLayout layoutRow = new HorizontalLayout();
        tfNombre = new TextField();
        tfCodigo = new TextField();
        HorizontalLayout layoutRow2 = new HorizontalLayout();
        cbOrigen = new ComboBox();
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
        setComboBoxSampleData(cbOrigen);
        tfCantidad.setLabel("Cantidad");
        tfCantidad.setWidth("300px");
        textMedium.setText("");
        textMedium.setWidth("max-content");
        textMedium.getStyle().set("font-size", "var(--lumo-font-size-m)");
        btGuardar.setText("Importar");
        btGuardar.setWidth("200px");
        btGuardar.setHeight("50px");
        btGuardar.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        btGuardar.addClickListener(e -> {
            btGuardar.getUI().ifPresent(ui ->
                    ui.navigate("lista-importaciones"));
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
            ProductoImportado productoBuscado = productoImportadoService.obtenerPorCodigo(codigo);
            tfNombre.setValue(productoBuscado.getNombre());
            tfCodigo.setValue(productoBuscado.getCodigo());
            cbOrigen.setValue(productoBuscado.getOrigen());
        }else {
            tfCodigo.setEnabled(true);
        }
    }

    record SampleItem(String value, String label, Boolean disabled) {
    }

    private void setComboBoxSampleData(ComboBox comboBox) {
        List<SampleItem> sampleItems = new ArrayList<>();
        sampleItems.add(new SampleItem("esmeraldas", "Esmeraldas", null));
        sampleItems.add(new SampleItem("manabi", "Manabi", null));
        sampleItems.add(new SampleItem("cayambe", "Cayambe", null));
        comboBox.setItems(sampleItems);
        comboBox.setItemLabelGenerator(item -> ((SampleItem) item).label());
    }
}
