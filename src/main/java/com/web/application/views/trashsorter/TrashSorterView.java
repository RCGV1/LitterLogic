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
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.web.application.backEndStuff.BinFinder;
import com.web.application.views.MainLayout;
import org.vaadin.lineawesome.LineAwesomeIcon;
import com.vaadin.flow.component.textfield.TextField;

import java.text.SimpleDateFormat;
import java.util.Date;

@PageTitle("Trash Sorter")
@Route(value = "Trash-Sorter", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
public class TrashSorterView extends VerticalLayout {

    public TrashSorterView() {
        VerticalLayout trashHelperBin = new VerticalLayout();
        HorizontalLayout resultLayout = new HorizontalLayout();
        VerticalLayout trashinputLayout = new VerticalLayout();
        setSpacing(true);
        TextField trashText = new TextField("Trash Description");
        trashText.setPlaceholder("Insert Description here!");
        trashText.setHeight("500px");
        trashText.setClearButtonVisible(true);
        trashText.setEnabled(true);
        trashText.setWidth("300px");
        H1 Title = new H1("Trash Sorter");
        Button search = new Button("Search");
        search.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        H3 Desc = new H3("Give us a description of trash and we will tell you what to do with it");
        Image imgDesc = new Image("https://www.folsom.ca.us/home/showpublishedimage/2400/637964229514730000","img Desc");
        imgDesc.setHeight("300px");
       VerticalLayout layout = new VerticalLayout();
        Title.getStyle().set("color", "green");
        Span status = new Span("Pending input text....");
        H3 result = new H3();
        H3 resultEmoji = new H3();
        Paragraph resultDesc = new Paragraph();
        Div spacer = new Div();
        spacer.setHeight("10px");
        status.getElement().getThemeList().add("badge contrast primary");
        Icon trashIcon = new Icon(VaadinIcon.TRASH);
        Icon recycleIcon = new Icon(VaadinIcon.RECYCLE);
        Icon compostIcon = new Icon(String.valueOf(LineAwesomeIcon.TREE_SOLID));
        Icon ewasteIcon = new Icon(String.valueOf(LineAwesomeIcon.BATTERY_HALF_SOLID));


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
                trashHelperBin.add(binDesc);
                resultDesc.setText("Created by Trash Adviser™ \uD83D\uDC68\u200D\uD83C\uDFEB :"+ binDesc);



                result.setText(foundBin);
                if (foundBin.equals("No keywords detected")){
                    status.setText("No Keywords \uD83D\uDFE5");
                } else {
                    SimpleDateFormat formatTime = new SimpleDateFormat("hh.mm aa");
                    Date date = new Date();
                    String time = formatTime.format(date);


                    status.setText("Sorted \uD83D\uDCC1 " + "generated time : "+ time + " \uD83D\uDD70️");



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
                trashHelperBin.add(binDesc);
                resultDesc.setText("Created by Trash Adviser™ \uD83D\uDC68\u200D\uD83C\uDFEB :"+ binDesc);




                result.setText(foundBin);
                if (foundBin.equals("No keywords detected")){
                    status.setText("No Keywords \uD83D\uDFE5");
                } else {
                    SimpleDateFormat formatTime = new SimpleDateFormat("hh.mm aa");
                    Date date = new Date();
                    String time = formatTime.format(date);


                    status.setText("Sorted \uD83D\uDCC1 " + "generated time : "+ time + " \uD83D\uDD70️");



                }
            }
        });





    }

}
