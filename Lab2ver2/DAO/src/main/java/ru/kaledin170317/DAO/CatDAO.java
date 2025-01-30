package ru.kaledin170317.DAO;

import jakarta.persistence.*;
import ru.kaledin170317.Entities.CatData;

import java.util.List;

public class CatDAO {

    private final EntityManagerFactory emf;

    public CatDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public void Save(CatData cat) {
        TransactionDAO.inTransaction(emf, entityManager -> {
            entityManager.persist(cat);
        });
    }

    public void Update(CatData cat) {
        TransactionDAO.inTransaction(emf, entityManager -> {
            entityManager.merge(cat);
        });
    }

    public List<CatData> getAllCats(){
        return emf.createEntityManager().createQuery("from CatData", CatData.class).getResultList();
    }
}
