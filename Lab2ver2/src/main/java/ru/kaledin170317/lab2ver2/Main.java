package ru.kaledin170317.lab2ver2;




import ru.kaledin170317.Controller.CatController;

import ru.kaledin170317.Controller.OwnerController;
import ru.kaledin170317.Entities.Color;

import java.util.Date;

import static jakarta.persistence.Persistence.*;



public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        var factory = createEntityManagerFactory("DataBase");

        CatController catController = new CatController(factory);
        OwnerController ownerController = new OwnerController(factory);


        var user1 = ownerController.createOwner("GH",new Date());
        var user2 = ownerController.createOwner("GH2",new Date());
        var user3 = ownerController.createOwner("GH3",new Date());

        var cat1 = catController.createCat("Ma", new Date(), user1, "Puma", Color.RED);
        var cat2 = catController.createCat("Ma", new Date(), user2, "Puma", Color.RED);

        catController.MakeFriendShip(cat1, cat2);

        var cat1fromDataBase = catController.getCatById(cat1.getId());

        System.out.println(catController.getCatById(cat1.getId()));
        System.out.println(cat1fromDataBase.getFriends().stream().count());
    }
}