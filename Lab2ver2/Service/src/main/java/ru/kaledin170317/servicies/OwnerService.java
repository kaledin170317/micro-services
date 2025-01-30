package ru.kaledin170317.servicies;

import ru.kaledin170317.Entities.OwnerData;
import jakarta.persistence.EntityManagerFactory;
import ru.kaledin170317.DAO.OwnerDAO;

import java.util.List;


public class OwnerService {

    private OwnerDAO ownerDAO;

    public OwnerService(EntityManagerFactory entityManagerFactory) {
        this.ownerDAO = new OwnerDAO(entityManagerFactory);
    }

    public OwnerData getById(int id){
        return ownerDAO.getAllCats().stream().filter(x -> x.getId() == id).findAny().orElse(null);
    }

    public void save(OwnerData owner){
        ownerDAO.save(owner);
    }

    public List<OwnerData> getAllOwners(){
        return ownerDAO.getAllCats();
    }
}
