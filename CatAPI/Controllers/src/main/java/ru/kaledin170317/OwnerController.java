package ru.kaledin170317;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import ru.kaledin170317.DTO.OwnerData;
import ru.kaledin170317.Entities.Owner;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/owners")
@RequiredArgsConstructor
public class OwnerController {

    private final OwnerService ownerService;

    @GetMapping(value = "")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Owner>> read() {
        var owners = ownerService.getAllOwners();

        List<Owner> ownerDTOS = new ArrayList<>();

        owners.forEach(owner -> {
            if(owner != null){
                ownerDTOS.add(new Owner(owner));
            }
        });

        return !ownerDTOS.isEmpty()
                ? new ResponseEntity<>(ownerDTOS, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Owner> getByID(@PathVariable int id) {
        var owner = ownerService.getOwnerByID(id).orElse(null);

        return owner != null ? new ResponseEntity<>(new Owner(owner), HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Boolean> create(@RequestBody OwnerData owner) {
        try{
            ownerService.createOwner(owner);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Boolean> update(@RequestBody OwnerData owner) {
        try{
            ownerService.updateOwner(owner);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Boolean> delete(@RequestBody OwnerData owner) {
        try{
            ownerService.deleteOwner(owner);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
