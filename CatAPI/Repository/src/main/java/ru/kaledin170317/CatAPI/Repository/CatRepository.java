package ru.kaledin170317.CatAPI.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kaledin170317.DTO.CatData;

@Repository
public interface CatRepository extends JpaRepository<CatData, Integer> {}