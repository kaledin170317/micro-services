package ru.kaledin170317.Controller;


import jakarta.persistence.EntityManagerFactory;
import lombok.NonNull;
import ru.kaledin170317.Entities.*;
import ru.kaledin170317.servicies.CatService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CatController {
    private CatService catService;

    public CatController(EntityManagerFactory factory){
        catService = new CatService(factory);
    }
    public Cat createCat(@NonNull String name,
                             Date birthday,
                             @NonNull Owner owner,
                             String breed,
                             Color color){
        CatData cat = CatData.builder()
                .birthday(birthday)
                .color(color)
                .owner(new OwnerData(owner))
                .name(name)
                .breed(breed)
                .friends(new ArrayList<>())
                .build();
        catService.save(cat);
        return new Cat(cat);
    }


    public List<Cat> getAllCats(){
        List<Cat> cats = new ArrayList<>();
        catService.getAllCats().forEach(x -> cats.add(new Cat(x)));
        return cats;
    }

    public void MakeFriendShip(Cat first_cat, Cat second_cat) {
        CatData k = catService.getCatByID(first_cat.getId());
        k.AddFriend(catService.getCatByID(second_cat.getId()));
        catService.update(k);
    }

    public Cat getCatById(int id) {
        return getAllCats().stream().filter(x -> x.getId() == id).findAny().orElse(null);
    }
}
