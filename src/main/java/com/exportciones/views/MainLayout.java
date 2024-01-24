package com.exportciones.views;

import com.exportciones.views.buscar.BuscarView;
import com.exportciones.views.directorio.DirectorioView;
import com.exportciones.views.listaexportaciones.ListaExportacionesView;
import com.exportciones.views.listaimportacion.ListaImportacionesView;
import com.exportciones.views.menu.MenuView;
import com.exportciones.views.nuevaexportacion.NuevaExportacionView;
import com.exportciones.views.nuevaimportacion.NuevaImportacionView;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.Footer;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Header;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.sidenav.SideNav;
import com.vaadin.flow.component.sidenav.SideNavItem;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.theme.lumo.LumoUtility;
import org.vaadin.lineawesome.LineAwesomeIcon;

/**
 * The main view is a top-level placeholder for other views.
 */
public class MainLayout extends AppLayout {

    private H2 viewTitle;

    public MainLayout() {
        setPrimarySection(Section.DRAWER);
        addDrawerContent();
        addHeaderContent();
    }

    private void addHeaderContent() {
        DrawerToggle toggle = new DrawerToggle();
        toggle.setAriaLabel("Menu toggle");

        viewTitle = new H2();
        viewTitle.addClassNames(LumoUtility.FontSize.LARGE, LumoUtility.Margin.NONE);

        addToNavbar(true, toggle, viewTitle);
    }

    private void addDrawerContent() {
        H1 appName = new H1("Exportacion");
        appName.addClassNames(LumoUtility.FontSize.LARGE, LumoUtility.Margin.NONE);
        Header header = new Header(appName);

        Scroller scroller = new Scroller(createNavigation());

        addToDrawer(header, scroller, createFooter());
    }

    private SideNav createNavigation() {
        SideNav nav = new SideNav();

        nav.addItem(new SideNavItem("Menu", MenuView.class, LineAwesomeIcon.GRIN_STARS.create()));
        nav.addItem(new SideNavItem("Nueva Importacion", NuevaImportacionView.class,
                LineAwesomeIcon.PENCIL_RULER_SOLID.create()));
        nav.addItem(new SideNavItem("Nueva Exportacion", NuevaExportacionView.class,
                LineAwesomeIcon.PENCIL_RULER_SOLID.create()));
        nav.addItem(new SideNavItem("Lista Importacion", ListaImportacionesView.class,
                LineAwesomeIcon.ADDRESS_BOOK_SOLID.create()));
        nav.addItem(new SideNavItem("Lista Exportaciones", ListaExportacionesView.class,
                LineAwesomeIcon.ADDRESS_BOOK_SOLID.create()));
        nav.addItem(new SideNavItem("Buscar", BuscarView.class, LineAwesomeIcon.MAP_PIN_SOLID.create()));
        nav.addItem(new SideNavItem("Directorio", DirectorioView.class, LineAwesomeIcon.BOOK_SOLID.create()));
        return nav;
    }

    private Footer createFooter() {
        Footer layout = new Footer();

        return layout;
    }

    @Override
    protected void afterNavigation() {
        super.afterNavigation();
        viewTitle.setText(getCurrentPageTitle());
    }

    private String getCurrentPageTitle() {
        PageTitle title = getContent().getClass().getAnnotation(PageTitle.class);
        return title == null ? "" : title.value();
    }
}
