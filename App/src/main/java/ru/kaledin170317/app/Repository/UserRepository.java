package ru.kaledin170317.app.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kaledin170317.app.Entities.UserDTO;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserDTO, Integer> {
    Optional<UserDTO> findByUsername(String username);
    boolean existsByUsername(String username);
}
