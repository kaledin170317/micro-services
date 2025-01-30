package ru.kaledin170317.ownersdb;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import ru.kaledin170317.ownersdb.Entities.CatsId;
import ru.kaledin170317.ownersdb.Entities.OwnerData;


import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OwnerService {

    private final OwnerRepository repository;
    private final CatsIdRepository catsIdRepository;


    public OwnerData save(OwnerData user) {
        return repository.save(user);
    }

    public OwnerData createOwner(OwnerData owner) {
        return repository.save(owner);
    }

    public List<OwnerData> getAllOwners() {
        return repository.findAll();
    }

    public OwnerData getOwnerByID(int id) {
        return repository.findById(id).get();
    }

    public OwnerData updateOwner(OwnerData owner) {
        owner.getCatsID().forEach(x -> catsIdRepository.save(x));
        return repository.save(owner);
    }

    public void deleteOwner(OwnerData owner) {
        repository.delete(owner);
    }
}
