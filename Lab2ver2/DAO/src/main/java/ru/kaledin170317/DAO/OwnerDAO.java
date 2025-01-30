package ru.kaledin170317.DAO;

import ru.kaledin170317.Entities.OwnerData;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;

public class OwnerDAO {

    private final EntityManagerFactory emf;



    public OwnerDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public void save(OwnerData owner) {
        TransactionDAO.inTransaction(emf, entityManager -> {
            entityManager.persist(owner);
        });
    }

    public List<OwnerData> getAllCats(){
        return emf.createEntityManager().createQuery("from OwnerData", OwnerData.class).getResultList();
    }
}
