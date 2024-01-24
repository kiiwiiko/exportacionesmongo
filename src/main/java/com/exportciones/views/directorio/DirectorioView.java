package com.exportciones.views.directorio;

import com.exportciones.models.Directorio;
import com.exportciones.views.MainLayout;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Lista Dierctorio")
@Route(value = "lista-directorios", layout = MainLayout.class)
@Uses(Icon.class)
public class DirectorioView extends Composite<VerticalLayout> {

    public DirectorioView() {

    }
}
