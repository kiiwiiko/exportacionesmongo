package com.exportciones.views.listas;

import com.exportciones.models.Exportar;
import com.exportciones.models.Producto;
import com.exportciones.services.ExportarService;
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


@PageTitle("Lista Exportaciones")
@Route(value = "lista-exportaciones", layout = MainLayout.class)
@Uses(Icon.class)
public class ListaExportacionesView extends Composite<VerticalLayout> {
    private ExportarService exportarService;


  public ListaExportacionesView(ExportarService exportarService) {
      this.exportarService=exportarService;

      getContent().setWidth("100%");
      getContent().getStyle().set("flex-grow", "1");

      Grid<Exportar> grid = new Grid<>(Exportar.class, false);
      grid.addColumn(Exportar::getNombre).setHeader("Nombre").setAutoWidth(true);
      grid.addColumn(Exportar::getCodigo).setHeader("Codigo").setAutoWidth(true);
      grid.addColumn(Exportar::getDestino).setHeader("Origen").setAutoWidth(true);
      grid.addColumn(Exportar::getCantidad).setHeader("Cantidad").setAutoWidth(true);

      List<Exportar> exportados = exportarService.listaExportaciones();
      grid.setItems(exportados);
      grid.addThemeVariants(GridVariant.LUMO_NO_BORDER);


      getContent().add(grid);

  }
}