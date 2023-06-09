package com.web.application.views;


import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.theme.lumo.LumoUtility;
import com.web.application.components.appnav.AppNav;
import com.web.application.components.appnav.AppNavItem;
import com.web.application.views.about.AboutView;
import com.web.application.views.trashinfo.TrashInfoView;
import com.web.application.views.trashsorter.TrashSorterView;
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
        toggle.getElement().setAttribute("aria-label", "Menu toggle");

        viewTitle = new H2();
        viewTitle.addClassNames(LumoUtility.FontSize.LARGE, LumoUtility.Margin.NONE);

        addToNavbar(true, toggle, viewTitle);
    }

    private void addDrawerContent() {
        H1 appName = new H1("Litter Logic");
        appName.addClassNames(LumoUtility.FontSize.LARGE, LumoUtility.Margin.NONE);
        Image logo = new Image("https://github.com/RCGV1/LitterLogic/blob/main/Assets/Litter%20Logic-1.png?raw=true","Logo");
        logo.setHeight("110px");
        Header header = new Header(appName,logo);

        Scroller scroller = new Scroller(createNavigation());

        addToDrawer(header, scroller, createFooter());
    }

    private AppNav createNavigation() {
        // AppNav is not yet an official component.
        // For documentation, visit https://github.com/vaadin/vcf-nav#readme
        AppNav nav = new AppNav();

        nav.addItem(new AppNavItem("Trash Sorter", TrashSorterView.class, LineAwesomeIcon.TRASH_SOLID.create()));
        nav.addItem(new AppNavItem("Trash Info", TrashInfoView.class, LineAwesomeIcon.INFO_CIRCLE_SOLID.create()));
        nav.addItem(new AppNavItem("About", AboutView.class, LineAwesomeIcon.ADDRESS_CARD_SOLID.create()));

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
