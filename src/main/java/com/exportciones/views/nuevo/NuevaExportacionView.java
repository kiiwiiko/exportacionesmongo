package com.exportciones.views.nuevo;

import com.exportciones.views.MainLayout;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.*;

@PageTitle("Nueva Exportacion")
@Route(value = "nueva-exportacion", layout = MainLayout.class)
@Uses(Icon.class)
public class NuevaExportacionView extends Composite<VerticalLayout> implements HasUrlParameter<String> {
    TextField tfNombre;
    TextField tfCodigo;
    ComboBox cbDestino;
    TextField tfCantidad;
    Button btGuardar;
    Button btMenu;

    String Seleccion;
    String valorTexto;
    @Override
    public void setParameter(BeforeEvent beforeEvent, @OptionalParameter String codigo) {
        this.codigo = codigo;
        if(codigo != null) {
        }else {

        }

    }
}
