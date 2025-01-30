package ru.kaledin170317;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.kaledin170317.CatAPI.Repository.CatRepository;
import ru.kaledin170317.CatAPI.Repository.OwnerRepository;
import ru.kaledin170317.DTO.OwnerData;
import ru.kaledin170317.Entities.Role;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OwnerService {

    private final OwnerRepository repository;


    public OwnerData save(OwnerData user) {
        return repository.save(user);
    }


    public OwnerData create(OwnerData user) {
        if (repository.existsByUsername(user.getUsername())) {
            throw new RuntimeException("Пользователь с таким именем уже существует");
        }

        return save(user);
    }


    public OwnerData getByUsername(String username) {
        return repository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));

    }

    public UserDetailsService userDetailsService() {
        return this::getByUsername;
    }


    public OwnerData getCurrentUser() {
        var username = SecurityContextHolder.getContext().getAuthentication().getName();
        return getByUsername(username);
    }


    @Deprecated
    public void getAdmin() {
        var user = getCurrentUser();
        user.setRole(Role.ROLE_ADMIN);
        save(user);
    }

    public void createOwner(OwnerData owner) {
        repository.save(owner);
    }

    public List<OwnerData> getAllOwners() {
        return repository.findAll();
    }

    public Optional<OwnerData> getOwnerByID(int id) {
        return repository.findById(id);
    }

    public void updateOwner(OwnerData owner) {
        repository.save(owner);
    }

    public void deleteOwner(OwnerData owner) {
        repository.delete(owner);
    }
}
