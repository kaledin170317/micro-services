package ru.kaledin170317.ownersdb;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kaledin170317.ownersdb.Entities.CatsId;
import ru.kaledin170317.ownersdb.Entities.OwnerData;
@Repository
interface  CatsIdRepository extends JpaRepository<CatsId, Integer> {
}
