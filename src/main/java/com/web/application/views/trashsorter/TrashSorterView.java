package com.web.application.views.trashsorter;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MultiFileBuffer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.server.StreamResource;
import com.vaadin.flow.theme.lumo.LumoIcon;
import com.vaadin.flow.theme.lumo.LumoUtility.Margin;
import com.web.application.views.MainLayout;
import org.vaadin.lineawesome.LineAwesomeIcon;

import java.io.InputStream;

@PageTitle("Trash Sorter")
@Route(value = "Trash-Sorter", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
public class TrashSorterView extends VerticalLayout {

    public TrashSorterView() {
        setSpacing(true);
        MultiFileBuffer buffer = new MultiFileBuffer();
        Upload upload = new Upload(buffer);
        upload.setMaxFiles(1);
        Button uploadButton = new Button("Upload Trash Here...");
        upload.setUploadButton(uploadButton);
        H1 Title = new H1("Trash Sorter");
        Div spacer = new Div();
        spacer.setHeight("200px");
        H3 Desc = new H3("Give us a image of trash and we will tell you what to do with it");
        Image imgDesc = new Image("https://www.millenniumwasteinc.com/assets/images/uploads/residentialservices.jpg","img Desc");
        imgDesc.setHeight("300px");
        imgDesc.setWidth("350px");
       VerticalLayout layout = new VerticalLayout();
        Title.getStyle().set("color", "blue");


       //Add components
       layout.add(Title);
       layout.add(Desc);
       layout.add(imgDesc);
       layout.add(spacer);
       layout.add(upload);

       //Properties
       layout.setSpacing(true);
       layout.setAlignItems(Alignment.CENTER);
       layout.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        add(layout);

        Image uploadedImg = new Image();
        upload.addSucceededListener(event ->{
            String fileName = event.getFileName();
            InputStream input = buffer.getInputStream(fileName);

            StreamResource resource = new StreamResource(fileName, () -> input);
            uploadedImg.setSrc(resource);
            uploadedImg.setWidth("350px");
            layout.addComponentAtIndex(3,uploadedImg);
            spacer.setHeight("0px");
        });

        upload.addAttachListener(event -> {
            upload.getElement().addEventListener("file-remove", domEvent -> {
                layout.remove(uploadedImg);
                spacer.setHeight("200px");
            });
        });

    }

}
