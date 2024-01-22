package com.exportciones.views.listaexportaciones;

import com.exportciones.views.MainLayout;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility.Gap;

@PageTitle("Lista Exportaciones")
@Route(value = "lista-exportaciones", layout = MainLayout.class)
@Uses(Icon.class)
public class ListaExportacionesView extends Composite<VerticalLayout> {

    public ListaExportacionesView() {
        H2 h2 = new H2();
        HorizontalLayout layoutRow = new HorizontalLayout();
        H3 h3 = new H3();
        H4 h4 = new H4();
        HorizontalLayout layoutRow2 = new HorizontalLayout();
        H3 h32 = new H3();
        H4 h42 = new H4();
        HorizontalLayout layoutRow3 = new HorizontalLayout();
        H3 h33 = new H3();
        H4 h43 = new H4();
        HorizontalLayout layoutRow4 = new HorizontalLayout();
        H3 h34 = new H3();
        H4 h44 = new H4();
        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");
        h2.setText("Producto");
        h2.setWidth("max-content");
        layoutRow.setWidthFull();
        getContent().setFlexGrow(1.0, layoutRow);
        layoutRow.addClassName(Gap.MEDIUM);
        layoutRow.setWidth("100%");
        layoutRow.setHeight("min-content");
        h3.setText("Nombre:");
        h3.setWidth("max-content");
        h4.setText("Heading");
        h4.setWidth("max-content");
        layoutRow2.setWidthFull();
        getContent().setFlexGrow(1.0, layoutRow2);
        layoutRow2.addClassName(Gap.MEDIUM);
        layoutRow2.setWidth("100%");
        layoutRow2.setHeight("min-content");
        h32.setText("Codigo:");
        h32.setWidth("max-content");
        h42.setText("Heading");
        h42.setWidth("max-content");
        layoutRow3.setWidthFull();
        getContent().setFlexGrow(1.0, layoutRow3);
        layoutRow3.addClassName(Gap.MEDIUM);
        layoutRow3.setWidth("100%");
        layoutRow3.setHeight("min-content");
        h33.setText("Cantidad:");
        h33.setWidth("max-content");
        h43.setText("Heading");
        h43.setWidth("max-content");
        layoutRow4.setWidthFull();
        getContent().setFlexGrow(1.0, layoutRow4);
        layoutRow4.addClassName(Gap.MEDIUM);
        layoutRow4.setWidth("100%");
        layoutRow4.setHeight("min-content");
        h34.setText("Destino:");
        h34.setWidth("max-content");
        h44.setText("Heading");
        h44.setWidth("max-content");
        getContent().add(h2);
        getContent().add(layoutRow);
        layoutRow.add(h3);
        layoutRow.add(h4);
        getContent().add(layoutRow2);
        layoutRow2.add(h32);
        layoutRow2.add(h42);
        getContent().add(layoutRow3);
        layoutRow3.add(h33);
        layoutRow3.add(h43);
        getContent().add(layoutRow4);
        layoutRow4.add(h34);
        layoutRow4.add(h44);
    }
}
