package com.exportciones.views.buscar;

import com.exportciones.views.MainLayout;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility.Gap;

@PageTitle("Buscar")
@Route(value = "buscar", layout = MainLayout.class)
@Uses(Icon.class)
public class BuscarView extends Composite<VerticalLayout> {

    public BuscarView() {
        VerticalLayout layoutColumn2 = new VerticalLayout();
        TextField textField = new TextField();
        Paragraph textMedium = new Paragraph();
        HorizontalLayout layoutRow = new HorizontalLayout();
        H4 h4 = new H4();
        H4 h42 = new H4();
        H4 h43 = new H4();
        H4 h44 = new H4();
        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");
        layoutColumn2.setWidthFull();
        getContent().setFlexGrow(1.0, layoutColumn2);
        layoutColumn2.setSpacing(false);
        layoutColumn2.setWidth("100%");
        layoutColumn2.getStyle().set("flex-grow", "1");
        textField.setLabel("Buscar Producto");
        textField.setWidth("500px");
        textMedium.setText("Error.");
        textMedium.setWidth("100%");
        textMedium.getStyle().set("font-size", "var(--lumo-font-size-m)");
        layoutRow.setWidthFull();
        layoutColumn2.setFlexGrow(1.0, layoutRow);
        layoutRow.addClassName(Gap.MEDIUM);
        layoutRow.setWidth("100%");
        layoutRow.getStyle().set("flex-grow", "1");
        h4.setText("Heading");
        h4.setWidth("max-content");
        h42.setText("Heading");
        h42.setWidth("max-content");
        h43.setText("Heading");
        h43.setWidth("max-content");
        h44.setText("Heading");
        h44.setWidth("max-content");
        getContent().add(layoutColumn2);
        layoutColumn2.add(textField);
        layoutColumn2.add(textMedium);
        layoutColumn2.add(layoutRow);
        layoutRow.add(h4);
        layoutRow.add(h42);
        layoutRow.add(h43);
        layoutRow.add(h44);
    }
}
