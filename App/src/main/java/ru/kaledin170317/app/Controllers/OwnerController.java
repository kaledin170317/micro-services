package ru.kaledin170317.app.Controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.server.ResponseStatusException;
import ru.kaledin170317.app.Entities.Owner;
import ru.kaledin170317.app.Entities.UserDTO;
import ru.kaledin170317.app.Servicies.OwnerService;
import ru.kaledin170317.app.Servicies.UserService;

@RestController
@RequestMapping("/owners")
@RequiredArgsConstructor
@Log4j2
public class OwnerController {

    private final OwnerService ownerService;
    private final UserService userService;

    private ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();

    @PostMapping(value = "")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Boolean> create(@RequestBody Owner owner) {
        try {
                ownerService.OwnerOperation(owner,"create");
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "/update")
    @PreAuthorize("hasRole('ADMIN')")
    public String update(@RequestBody Owner owner) {
        try {
            return ow.writeValueAsString(ownerService.OwnerOperation(owner, "update"));
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }

    @DeleteMapping(value = "/delete")
    @PreAuthorize("hasRole('ADMIN')")
    public String delete(@PathVariable int id) {
        try {
            userService.deleteOwner(new UserDTO(id));
            return ow.writeValueAsString(ownerService.OwnerOperation(new Owner(id), "delete"));
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }

    @GetMapping(value = "/read/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String read() {
        try {
            return ow.writeValueAsString(ownerService.OwnerOperation(new Owner(userService.getCurrentUser().getId()), "read"));
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }


}
