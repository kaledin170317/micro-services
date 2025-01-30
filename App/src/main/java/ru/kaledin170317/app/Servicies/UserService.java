package ru.kaledin170317.app.Servicies;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.kaledin170317.app.Entities.Role;
import ru.kaledin170317.app.Entities.UserDTO;
import ru.kaledin170317.app.Repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;



    public UserDTO save(UserDTO user) {
        return repository.save(user);
    }


    public UserDTO create(UserDTO user) {
        if (repository.existsByUsername(user.getUsername())) {
            throw new RuntimeException("Пользователь с таким именем уже существует");
        }
        return save(user);
    }


    public UserDTO getByUsername(String username) {
        return repository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));

    }

    public UserDetailsService userDetailsService() {
        return this::getByUsername;
    }


    public UserDTO getCurrentUser() {
        var username = SecurityContextHolder.getContext().getAuthentication().getName();
        return getByUsername(username);
    }


    @Deprecated
    public void getAdmin() {
        var user = getCurrentUser();
        user.setRole(Role.ROLE_ADMIN);
        save(user);
    }

    public void createOwner(UserDTO owner) {
        repository.save(owner);
    }

    public List<UserDTO> getAllOwners() {
        return repository.findAll();
    }

    public Optional<UserDTO> getOwnerByID(int id) {
        return repository.findById(id);
    }

    public void updateOwner(UserDTO owner) {
        repository.save(owner);
    }

    public void deleteOwner(UserDTO owner) {
        repository.delete(owner);
    }
}
