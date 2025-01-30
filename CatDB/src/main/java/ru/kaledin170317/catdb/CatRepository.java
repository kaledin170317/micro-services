package ru.kaledin170317.catdb;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kaledin170317.catdb.Entites.CatData;

@Repository
public interface CatRepository extends JpaRepository<CatData, Integer> {}
