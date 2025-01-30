package ru.kaledin170317.CatAPI.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kaledin170317.DTO.OwnerData;

import java.util.Optional;

public interface OwnerRepository extends JpaRepository<OwnerData, Integer> {
    Optional<OwnerData> findByUsername(String username);
    boolean existsByUsername(String username);
}
