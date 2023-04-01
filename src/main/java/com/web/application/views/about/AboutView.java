package com.web.application.views.about;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility.Margin;
import com.web.application.views.MainLayout;

@PageTitle("About")
@Route(value = "About", layout = MainLayout.class)
public class AboutView extends VerticalLayout {

    public AboutView() {
     VerticalLayout layout = new VerticalLayout();
        H1 Title = new H1("About This Project");
        Title.getStyle().set("color", "green");

        H2 desc = new H2("This project was made for Wilhacks 4.0 by :");



        layout.add(Title);
        layout.add(desc);

        //Properties
        layout.setSpacing(true);
        layout.setAlignItems(Alignment.CENTER);
        layout.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);

        add(layout);



    }

}
