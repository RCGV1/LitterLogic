package com.web.application.views.trashsorter;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MultiFileBuffer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.theme.lumo.LumoUtility.Margin;
import com.web.application.views.MainLayout;

import java.io.InputStream;

@PageTitle("Trash Sorter")
@Route(value = "Trash-Sorter", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
public class TrashSorterView extends VerticalLayout {

    public TrashSorterView() {
        setSpacing(true);
        MultiFileBuffer buffer = new MultiFileBuffer();
        Upload upload = new Upload(buffer);
        H1 Title = new H1("Trash Sorter");
        H2 Desc = new H2("Give us a image of trash and we will tell you what to do with it");
        Image imgDesc = new Image("https://www.millenniumwasteinc.com/assets/images/uploads/residentialservices.jpg","img Desc");
        imgDesc.setHeight("300px");
        imgDesc.setWidth("350px");
       VerticalLayout layout = new VerticalLayout();

       //Add components
       layout.add(Title);
       layout.add(Desc);
       layout.add(imgDesc);
       layout.add(upload);

       //Properties
       layout.setSpacing(true);
       layout.setAlignItems(Alignment.CENTER);
       layout.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        add(layout);


        upload.addSucceededListener(event ->{
            String fileName = event.getFileName();
            InputStream input = buffer.getInputStream(fileName);


        });
    }

}
