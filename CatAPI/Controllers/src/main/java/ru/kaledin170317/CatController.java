package ru.kaledin170317;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import ru.kaledin170317.DTO.CatData;
import ru.kaledin170317.Entities.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/cats")
@RequiredArgsConstructor
public class CatController {

    private final CatService catsService;

    @GetMapping(value = "/user")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<Cat>> read(Authentication authentication) {
        var cats = catsService.getAllCats();
        cats.removeIf(n -> n.getOwner().getUsername().equals(authentication.getName()));

        List<Cat> catss = new ArrayList<>();

        cats.forEach(cat -> {
            if (cat != null){
                catss.add(new Cat(cat));
            }
        });

        return !catss.isEmpty()
                ? new ResponseEntity<>(catss, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Cat>> read_admin(Authentication authentication) {
        var cats = catsService.getAllCats();

        List<Cat> catss = new ArrayList<>();

        cats.forEach(cat -> {
            if (cat != null){
                catss.add(new Cat(cat));
            }
        });

        return !catss.isEmpty()
                ? new ResponseEntity<>(catss, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Cat> getByID(@PathVariable int id) {
        var cat = catsService.getCatByID(id).orElse(null);

        return cat != null ? new ResponseEntity<>(new Cat(cat), HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Boolean> create(@RequestBody CatData cat) {
        try{
            catsService.createCat(cat);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "/friend")
    public ResponseEntity<Boolean> addFriend(@RequestBody Friend friend) {
        try{
            catsService.makeFriend(friend.getCatId1(),friend.getCatId2());
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Boolean> update(@RequestBody Cat cat) {
        try{
            catsService.updateCat(new CatData(cat));
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    @DeleteMapping(value = "")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Boolean> delete(@RequestBody CatData cat) {
        try{
            catsService.deleteCat(cat);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
