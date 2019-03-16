package com.gokhan.veteriner.ui;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.gokhan.veteriner.domain.Animal;
import com.gokhan.veteriner.service.AnimalService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.provider.ListDataProvider;
import javafx.scene.control.SingleSelectionModel;

import java.util.List;
import java.util.Set;

public class AnimalComponent extends HorizontalLayout {

    private AnimalService animalService;
    private List<Animal> animals;

    public AnimalComponent(AnimalService animalService){
        this.animalService = animalService;
        setSizeFull();
        buildComponent();
    }


    private void buildComponent() {

        animals= animalService.findAll();

        Grid<Animal> animalGrid = new Grid<>();

//        // grid selection...(Single)
        animalGrid.setSelectionMode(Grid.SelectionMode.SINGLE);

        ListDataProvider<Animal> animalListDataProvider = new ListDataProvider<>(animals);
        animalGrid.setDataProvider(animalListDataProvider);

//        animalGrid.addColumn(Animal::getPk).setHeader("Pk");
        animalGrid.addColumn(Animal::getSpecies).setHeader("Species");
        animalGrid.addColumn(Animal::getName).setHeader("Name");
        animalGrid.addColumn(Animal::getAge).setHeader("Age");
        animalGrid.addColumn(Animal::getHeight).setHeader("Height");
        animalGrid.addColumn(Animal::getWeight).setHeader("Weight");
        animalGrid.addColumn(Animal::getEyecolor).setHeader("EyeColor");

        animalGrid.setSizeFull();
        animalListDataProvider.refreshAll();

        VerticalLayout verticalLayoutLeft = new VerticalLayout();
        verticalLayoutLeft.getElement().getStyle().set("background-color", "white");
        verticalLayoutLeft.setHeight("100%");
        verticalLayoutLeft.setWidth("70%");
        verticalLayoutLeft.setMargin(false);
        verticalLayoutLeft.getElement().setAttribute("theme","");

        VerticalLayout verticalLayoutRight = new VerticalLayout();
        verticalLayoutRight.getElement().getStyle().set("background-color", "white");
        verticalLayoutRight.setHeight("100%");
        verticalLayoutRight.setWidth("30%");
//        verticalLayoutRight.getElement().setAttribute("theme","");
        verticalLayoutRight.setMargin(false);
        verticalLayoutRight.setPadding(false);

        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.getElement().getStyle().set("background-color", "white");
        horizontalLayout.setWidth("100%");
        horizontalLayout.setHeight("30%");
        horizontalLayout.setPadding(false);
        horizontalLayout.setMargin(false);

        HorizontalLayout vaccinlayout = new HorizontalLayout();
        vaccinlayout.getElement().getStyle().set("background-color","white");
        vaccinlayout.setWidth("100%");
        vaccinlayout.setHeight("7%");
        vaccinlayout.setPadding(false);
        vaccinlayout.setMargin(false);

        TextField speciestextField = new TextField("Patient Species");
        speciestextField.setPlaceholder("Enter Patient species...");
        speciestextField.getElement().getStyle().set("padding","0px");

        TextField nametextField = new TextField("Patient Name");
        nametextField.setPlaceholder("Enter Patient Name...");
        nametextField.getElement().getStyle().set("padding","0px");

        TextField agetextField1 = new TextField("Age");
        agetextField1.setPlaceholder("Enter Age");
        agetextField1.getElement().getStyle().set("padding","0px");

        TextField heighttextField2 = new TextField("Height");
        heighttextField2.setPlaceholder("Enter Height");
        heighttextField2.getElement().getStyle().set("padding","0px");

        TextField weighttextField3 = new TextField("Weight");
        weighttextField3.setPlaceholder("Enter Weight");
        weighttextField3.getElement().getStyle().set("padding","0px");

        TextField eyecolortextField4 = new TextField("EyeColor");
        eyecolortextField4.setPlaceholder("Enter EyeColor");
        eyecolortextField4.getElement().getStyle().set("padding","0px");

        TextField vaccinInfo = new TextField();
        vaccinInfo.setWidth("100%");

        Button savebutton = new Button("OK");
        Button cancelbutton = new Button("Cancel");
        Button vaccinationbutton = new Button("Show Vaccination Schedule");
        vaccinationbutton.setSizeFull();


        List<Animal> newagedList = animalService.findAll();  // yazmasak olabilir gibi...?


        add(verticalLayoutLeft);
        add(verticalLayoutRight);

        verticalLayoutLeft.add(animalGrid);
        verticalLayoutLeft.add(vaccinlayout);
        vaccinlayout.add(vaccinationbutton);
        vaccinlayout.add(vaccinInfo);

        verticalLayoutRight.add(speciestextField);
        verticalLayoutRight.add(nametextField);
        verticalLayoutRight.add(agetextField1);
        verticalLayoutRight.add(heighttextField2);
        verticalLayoutRight.add(weighttextField3);
        verticalLayoutRight.add(eyecolortextField4);

        verticalLayoutRight.add(horizontalLayout);
        horizontalLayout.add(savebutton);
        horizontalLayout.add(cancelbutton);

// adding a save button to make new animals...

        savebutton.addClickListener(c->{

            Animal anim = new Animal();
            anim.setSpecies(speciestextField.getValue());
            anim.setName(nametextField.getValue());
            anim.setAge(Integer.parseInt(agetextField1.getValue()));
            anim.setHeight(Double.parseDouble(heighttextField2.getValue()));
            anim.setWeight(Double.parseDouble(weighttextField3.getValue()));
            anim.setEyecolor(eyecolortextField4.getValue());

            saveAnimalToDatabase(anim);

            animals.clear();
            animals.addAll(animalService.findAll());
            animalListDataProvider.refreshAll();

            speciestextField.clear();
            nametextField.clear();
            agetextField1.clear();
            heighttextField2.clear();
            weighttextField3.clear();
            eyecolortextField4.clear();
        });

// adding a cancel button to remove an animal from grid via database...

        cancelbutton.addClickListener(c->{

            Set<Animal> selected = animalGrid.getSelectedItems();
            Animal animal= selected.stream().findFirst().get();
            animalService.delete(animal);
            animals.clear();
            animals.addAll(animalService.findAll());
            animalListDataProvider.refreshAll();
            speciestextField.clear();
            nametextField.clear();
            agetextField1.clear();
            heighttextField2.clear();
            weighttextField3.clear();
            eyecolortextField4.clear();

        });

        // adding an update field...
        animalGrid.addSelectionListener(c -> {
            Set<Animal> selected = animalGrid.getSelectedItems();
            Animal selection = selected.stream().findFirst().get();
            speciestextField.setValue(selection.getSpecies());
            nametextField.setValue(selection.getName());
            agetextField1.setValue(Integer.toString(selection.getAge()));
            heighttextField2.setValue(Double.toString(selection.getHeight()));
            weighttextField3.setValue(Double.toString(selection.getWeight()));
            eyecolortextField4.setValue(selection.getEyecolor());
            vaccinInfo.clear();

            


            // adding vaccination schedule...
            vaccinationbutton.addClickListener(v ->{

                vaccinInfo.setValue(vaccinate(selection));
            });


        });


    }

    String vaccinate(Animal selection){

        String s = new String();

        if (selection.getAge() % 3 == 0){
            s = selection.getName() + ", time to vaccinate...";}

        else if(selection.getAge() % 3 == 1){
            s = selection.getName() + ", come in two months...";}

        else if (selection.getAge() % 3 == 2){
            s = selection.getName() + ", come next month...";
        }
        return s;

    }

    void update(Animal animal){
        animalService.update(animal);
    }

    void saveAnimalToDatabase(Animal animal){

        animalService.insert(animal);
    }

}


