package com.web.application.views.trashinfo;

import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility.Margin;
import com.web.application.views.MainLayout;

@PageTitle("Trash Info")
@Route(value = "Trash-Info", layout = MainLayout.class)
public class TrashInfoView extends VerticalLayout {

    public TrashInfoView() {
        VerticalLayout layout = new VerticalLayout();
        HorizontalLayout TitleLayout = new HorizontalLayout();
        H1 Title = new H1("Trash Info");
        Image infoIcn = new Image("https://upload.wikimedia.org/wikipedia/commons/4/43/Minimalist_info_Icon.png","Info Icon");
        H4 description = new H4("Learning about disposing waste can be a daunting task but bellow is some useful information about waste");
        infoIcn.setHeight("40px");
        TitleLayout.add(infoIcn);
        TitleLayout.add(Title);
        layout.add(TitleLayout);
        layout.add(description);
        Title.getStyle().set("color", "blue");

        //Properties
        layout.setSpacing(true);
        layout.setAlignItems(Alignment.CENTER);
        TitleLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        layout.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);


        //Trash Layout
        Div spacer = new Div();
        spacer.setWidth("30px");
        HorizontalLayout mainTrashlayout = new HorizontalLayout();
        HorizontalLayout Compost = new HorizontalLayout();
        HorizontalLayout Trash = new HorizontalLayout();
        HorizontalLayout Recycle = new HorizontalLayout();
        Image CompostImg = new Image("https://www.recology.com/wp-content/uploads/2016/10/CompostBin.jpg","Compost Image");
        Image TrashImg = new Image("https://www.recology.com/wp-content/uploads/2016/10/RecycleBin.jpg","Trash Image");
        Image RecycleImage = new Image("https://www.recology.com/wp-content/uploads/2016/10/LandfillBin.jpg","Recycle Image");

        H6 compostDesc = new H6("Food scraps, soiled paper, & yard trimmings go in the compost cart. Want to compost? ");
        Compost.add(CompostImg);
        Compost.add(compostDesc);
        H6 trashDesc = new H6("Paper, cardboard, glass bottles, aluminum/tin cans, hard plastics, bundled plastic bags & thin (film) plastics go into the recyclables cart.");
        Trash.add(TrashImg);
        Trash.add(trashDesc);
        H6 recycleDesc = new H6("What is trash? Not much! Cat litter, ceramics, & broken glass to name a few.  Less of these items placed in the landfill cart the better.");
        Recycle.add(RecycleImage);
        Recycle.add(recycleDesc);

        mainTrashlayout.add(Compost);

        mainTrashlayout.add(spacer);

        mainTrashlayout.add(Trash);

        mainTrashlayout.add(spacer);

        mainTrashlayout.add(Recycle);



        add(layout);

        add(mainTrashlayout);
    }

}
