package com.gokhan.veteriner.ui;

import com.gokhan.veteriner.service.AnimalService;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "", layout = MainAppLayout.class)
public class AnimalView extends VerticalLayout {
    private AnimalService animalService;

    @Autowired
    public AnimalView(AnimalService animalService) {
        this.animalService = animalService;
        setSizeFull();
        AnimalComponent animalComponent = new AnimalComponent(animalService);
        add(animalComponent);

    }
}
