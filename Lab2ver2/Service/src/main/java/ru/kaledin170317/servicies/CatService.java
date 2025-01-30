package ru.kaledin170317.servicies;

import jakarta.persistence.EntityManagerFactory;
import ru.kaledin170317.DAO.CatDAO;
import ru.kaledin170317.Entities.CatData;

import java.util.List;


public class CatService {
    private final CatDAO catDAO;

    public CatService(EntityManagerFactory entityManagerFactory) {
        this.catDAO = new CatDAO(entityManagerFactory);
    }

    public CatData getCatByID(int id){
        return catDAO.getAllCats().stream().filter(x -> x.getId()== id).findAny().orElse(null);
    }

    public void save(CatData cat){
        catDAO.Save(cat);
    }

    public void update(CatData cat){
        catDAO.Update(cat);
    }

    public List<CatData> getAllCats(){
        return catDAO.getAllCats();
    }

}
