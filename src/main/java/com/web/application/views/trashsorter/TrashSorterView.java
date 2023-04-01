package com.web.application.views.trashsorter;

import com.vaadin.flow.component.Key;
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
        trashText.setPlaceholder("Insert Description here!");
        trashText.setHeight("500px");
        trashText.setClearButtonVisible(true);
        trashText.setEnabled(true);
        trashText.setWidth("500px");
        H1 Title = new H1("Trash Sorter");
        Button search = new Button("Search");
        search.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        H3 Desc = new H3("Give us a description of trash and we will tell you what to do with it");
        Image imgDesc = new Image("https://github.com/RCGV1/LitterLogic/blob/main/Assets/My%20project-1-2.jpg?raw=true","img Desc");
        imgDesc.setHeight("300px");
        imgDesc.setWidth("300px");
       VerticalLayout layout = new VerticalLayout();
        Title.getStyle().set("color", "green");
        Span status = new Span("Pending input text....");
        H3 result = new H3();
        Paragraph resultDesc = new Paragraph();
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
        layout.add(resultDesc);
        layout.add(search,trashText);

        Anchor anchor = new Anchor("/Trash-Info", "Learn more about trash");
        anchor.getElement().setAttribute("target", "_Trash-Info");

        layout.add((anchor));
        //Properties
       layout.setSpacing(true);
       layout.setAlignItems(Alignment.CENTER);
       layout.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        add(layout);
        trashText.addKeyPressListener(Key.ENTER, keyPressEvent -> {

            BinFinder binFinder = new BinFinder();

            if (trashText.getValue().equals("")){
                System.out.println("null");
                status.setText("No Input");
            } else{
                System.out.println(trashText.getValue());
                String foundBin = binFinder.findBin(trashText.getValue());
                System.out.println(foundBin);
                String binDesc = binFinder.getBinDisc(foundBin);
                resultDesc.setText(binDesc);
                result.setText(foundBin);

                if (foundBin.equals("No keywords detected")){
                    status.setText("No Keywords");
                } else {
                    status.setText("Sorted");
                }
            }
                });
        search.addClickListener(e -> {

            BinFinder binFinder = new BinFinder();

            if (trashText.getValue().equals("")){
                System.out.println("null");
                status.setText("No Input");
            } else{
                System.out.println(trashText.getValue());
               String foundBin = binFinder.findBin(trashText.getValue());
                System.out.println(foundBin);
                String binDesc = binFinder.getBinDisc(foundBin);
                resultDesc.setText(binDesc);
                result.setText(foundBin);

                if (foundBin.equals("No keywords detected")){
                    status.setText("No Keywords");
                } else {
                    status.setText("Sorted");
                }
            }
        });





    }

}
