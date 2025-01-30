package ru.kaledin170317.catdb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kaledin170317.catdb.Entites.CatData;

@Service
public class CatService {
    CatRepository catRepository;

    @Autowired
    public CatService(CatRepository catRepository) {
        this.catRepository = catRepository;
    }

    public CatData createCat(CatData cat) {
        return  catRepository.save(cat);
    }

    public CatData getCatByID(int id) {
        return catRepository.findById(id).get();
    }

    public CatData updateCat(CatData cat) {
         return catRepository.save(cat);
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
