package ru.kaledin170317.ownersdb;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kaledin170317.ownersdb.Entities.OwnerData;

@Repository
public interface OwnerRepository extends JpaRepository<OwnerData, Integer> {
}
