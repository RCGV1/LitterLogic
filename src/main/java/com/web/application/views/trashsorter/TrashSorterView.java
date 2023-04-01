package com.web.application.views.trashsorter;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MultiFileBuffer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.server.StreamResource;
import com.vaadin.flow.theme.lumo.LumoIcon;
import com.vaadin.flow.theme.lumo.LumoUtility.Margin;
import com.web.application.backEndStuff.BinFinder;
import com.web.application.views.MainLayout;
import org.vaadin.lineawesome.LineAwesomeIcon;
import com.vaadin.flow.component.textfield.TextField;

import java.awt.*;
import java.io.InputStream;

import com.web.application.backEndStuff.BinFinder;

@PageTitle("Trash Sorter")
@Route(value = "Trash-Sorter", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
public class TrashSorterView extends VerticalLayout {

    public TrashSorterView() {

        VerticalLayout trashinputLayout = new VerticalLayout();
        setSpacing(true);
        TextField trashText = new TextField("Trash Description");
        trashText.setHeight("500px");
        trashText.setClearButtonVisible(true);
        trashText.setEnabled(true);
        H1 Title = new H1("Trash Sorter");
        Button search = new Button("Search");
        search.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        H3 Desc = new H3("Give us a description of trash and we will tell you what to do with it");
        Image imgDesc = new Image("https://www.millenniumwasteinc.com/assets/images/uploads/residentialservices.jpg","img Desc");
        imgDesc.setHeight("300px");
        imgDesc.setWidth("350px");
       VerticalLayout layout = new VerticalLayout();
        Title.getStyle().set("color", "blue");
        Span status = new Span("Pending input text....");
        H5 result = new H5();

        Div spacer = new Div();
        spacer.setHeight("30px");
        status.getElement().getThemeList().add("badge contrast primary");
       //Add components
       layout.add(Title);
       layout.add(Desc);
       layout.add(status);
        layout.add(imgDesc);
        layout.add(spacer);
        layout.add(result);
        layout.add(search,trashText);

        Anchor anchor = new Anchor("/Trash-Info", "Learn more about trash");
        anchor.getElement().setAttribute("target", "_Trash-Info");

        layout.add((anchor));
        //Properties
       layout.setSpacing(true);
       layout.setAlignItems(Alignment.CENTER);
       layout.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        add(layout);

        search.addClickListener(e -> {

            BinFinder binFinder = new BinFinder();

            if (trashText.getValue() == null){
                System.out.println("null");
            } else{
               String foundBin = binFinder.frontTest(trashText.getValue());
                System.out.println(foundBin);
                result.setText(foundBin);
            }
        });





    }

}
