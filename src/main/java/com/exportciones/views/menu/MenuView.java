package com.exportciones.views.menu;

import com.exportciones.views.MainLayout;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.theme.lumo.LumoUtility.Padding;

@PageTitle("Menu")
@Route(value = "menu", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
@Uses(Icon.class)
public class MenuView extends Composite<VerticalLayout> {

    public MenuView() {
        H2 h2 = new H2();
        VerticalLayout layoutColumn2 = new VerticalLayout();
        Button buttonPrimary = new Button();
        Button buttonPrimary2 = new Button();
        Button buttonPrimary3 = new Button();
        Button buttonPrimary4 = new Button();
        Button buttonPrimary5 = new Button();
        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");
        h2.setText("Bienvenidos");
        getContent().setAlignSelf(FlexComponent.Alignment.CENTER, h2);
        h2.setWidth("max-content");
        layoutColumn2.setWidthFull();
        getContent().setFlexGrow(1.0, layoutColumn2);
        layoutColumn2.addClassName(Padding.SMALL);
        layoutColumn2.setWidth("100%");
        layoutColumn2.getStyle().set("flex-grow", "1");
        layoutColumn2.setJustifyContentMode(JustifyContentMode.START);
        layoutColumn2.setAlignItems(Alignment.CENTER);
        buttonPrimary.setText("Nueva Importacion");
        layoutColumn2.setAlignSelf(FlexComponent.Alignment.CENTER, buttonPrimary);
        buttonPrimary.setWidth("200px");
        buttonPrimary.setHeight("50px");
        buttonPrimary.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buttonPrimary.addClickListener(e -> {
            buttonPrimary.getUI().ifPresent(ui ->
                    ui.navigate("nueva-importacion"));
        });
        buttonPrimary2.setText("Nueva Exportacion");
        layoutColumn2.setAlignSelf(FlexComponent.Alignment.CENTER, buttonPrimary2);
        buttonPrimary2.setWidth("200px");
        buttonPrimary2.setHeight("50px");
        buttonPrimary2.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buttonPrimary2.addClickListener(e -> {
            buttonPrimary2.getUI().ifPresent(ui ->
                    ui.navigate("nueva-exportacion"));
        });
        buttonPrimary3.setText("Importaciones");
        layoutColumn2.setAlignSelf(FlexComponent.Alignment.CENTER, buttonPrimary3);
        buttonPrimary3.setWidth("200px");
        buttonPrimary3.setHeight("50px");
        buttonPrimary3.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buttonPrimary3.addClickListener(e -> {
            buttonPrimary3.getUI().ifPresent(ui ->
                    ui.navigate("lista-productos"));
        });
        buttonPrimary4.setText("Exportaciones");
        layoutColumn2.setAlignSelf(FlexComponent.Alignment.CENTER, buttonPrimary4);
        buttonPrimary4.setWidth("200px");
        buttonPrimary4.setHeight("50px");
        buttonPrimary4.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buttonPrimary4.addClickListener(e -> {
            buttonPrimary4.getUI().ifPresent(ui ->
                    ui.navigate("lista-exportados"));
        });
        buttonPrimary5.setText("Buscar");
        layoutColumn2.setAlignSelf(FlexComponent.Alignment.CENTER, buttonPrimary4);
        buttonPrimary5.setWidth("200px");
        buttonPrimary5.setHeight("50px");
        buttonPrimary5.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buttonPrimary5.addClickListener(e -> {
            buttonPrimary5.getUI().ifPresent(ui ->
                    ui.navigate("buscar"));
        });
        getContent().add(h2);
        getContent().add(layoutColumn2);
        layoutColumn2.add(buttonPrimary);
        layoutColumn2.add(buttonPrimary2);
        layoutColumn2.add(buttonPrimary3);
        layoutColumn2.add(buttonPrimary4);
        layoutColumn2.add(buttonPrimary5);
    }
}
