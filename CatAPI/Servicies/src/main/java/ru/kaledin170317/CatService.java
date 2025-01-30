package ru.kaledin170317;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kaledin170317.CatAPI.Repository.CatRepository;
import ru.kaledin170317.DTO.CatData;

import java.util.List;
import java.util.Optional;

@Service
public class CatService {
    CatRepository catRepository;

    @Autowired
    public CatService(CatRepository catRepository) {
        this.catRepository = catRepository;
    }

    public void createCat(CatData cat) {
        catRepository.save(cat);
    }

    public List<CatData> getAllCats() {
        return catRepository.findAll();
    }

    public List<CatData> getAllCatsByUsername(String username) {
        List<CatData> list = catRepository.findAll();
        list.removeIf(n -> username != n.getOwner().getUsername());
        return list;
    }

    public Optional<CatData> getCatByID(int id) {
        return catRepository.findById(id);
    }

    public void updateCat(CatData cat) {
        catRepository.save(cat);
    }

    public void deleteCat(CatData cat) {
        catRepository.delete(cat);
    }

    public void makeFriend(Integer cat, Integer friend) {

        var tmpcat1 = catRepository.findById(cat);
        var tmpcat2 = catRepository.findById(friend);

        tmpcat1.get().getFriends().add(tmpcat2.get());
        tmpcat2.get().getFriends().add(tmpcat1.get());

        catRepository.save(tmpcat1.get());
        catRepository.save(tmpcat2.get());
    }
}
