package com.web.application.views.about;

import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility.Margin;
import com.web.application.views.MainLayout;

@PageTitle("About")
@Route(value = "About", layout = MainLayout.class)
public class AboutView extends VerticalLayout {

    public AboutView() {
        HorizontalLayout peopleLayout = new HorizontalLayout();
        VerticalLayout prateekLayout = new VerticalLayout();
        VerticalLayout benjaminLayout = new VerticalLayout();
     VerticalLayout layout = new VerticalLayout();
        H1 Title = new H1("About This Project");
        Title.getStyle().set("color", "green");

        H2 desc = new H2("This project was made for Wilhacks 4.0 by :");

        H3 Title1 = new H3("Benjamin Faershtein \uD83D\uDC68\u200D\uD83D\uDCBB");
        H3 Title2 = new H3("Prateek Gupta \uD83D\uDC68\u200D\uD83D\uDCBB");
        Paragraph desc1 = new Paragraph("Worked on the Front-end");
        Paragraph desc2 = new Paragraph("Worked on the Back-end");

        benjaminLayout.add(Title1,desc1);
        prateekLayout.add(Title2,desc2);

        peopleLayout.add(benjaminLayout,prateekLayout);

        layout.add(Title);
        layout.add(desc);
        layout.add(peopleLayout);


        //Properties
        layout.setSpacing(true);
        layout.setAlignItems(Alignment.CENTER);
        layout.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);

        add(layout);



    }

}
