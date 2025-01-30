package ru.kaledin170317.app.Controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.kaledin170317.app.Entities.Cat;
import ru.kaledin170317.app.Entities.MakeFriendRequest;
import ru.kaledin170317.app.Entities.Owner;
import ru.kaledin170317.app.Entities.Role;
import ru.kaledin170317.app.Servicies.CatService;
import ru.kaledin170317.app.Servicies.OwnerService;
import ru.kaledin170317.app.Servicies.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

@RestController
@RequestMapping("/cats")
@RequiredArgsConstructor
@Log4j2
public class CatController {

    private final CatService catService;
    private final UserService userService;
    private final OwnerService ownerService;

    private ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();

    @PostMapping(value = "")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public Cat create(@RequestBody Cat cat) {
        try {
            cat.setOwnerID(userService.getCurrentUser().getId());
            var owner = ownerService.OwnerOperation(new Owner(userService.getCurrentUser().getId()),"read");
            var tmpcat = catService.CatOperations(cat,"create");
            owner.AddCatId(tmpcat.getId());
            System.out.println(ow.writeValueAsString(owner));
            ownerService.OwnerOperation(owner, "update");
            return tmpcat;
        } catch (Exception ex) {
            return null;
        }
    }

    @GetMapping(value = "/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public String read(@PathVariable int id) {

        try {
            if (userService.getCurrentUser().getRole() == Role.ROLE_ADMIN) {
                return ow.writeValueAsString(catService.CatOperations(new Cat(id), "read"));
            }

            if (userService.getCurrentUser().getRole() == Role.ROLE_USER) {
                var cat = catService.CatOperations(new Cat(id), "read");
                if (cat.getOwnerID() == userService.getCurrentUser().getId()) {
                    return ow.writeValueAsString(cat);
                } else {
                    return "Нет доступа к коту";
                }
            }

            return "Неисвестная роль";
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }


    @DeleteMapping(value = "")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public String delete(@PathVariable int id) {
        try {

            if (userService.getCurrentUser().getRole() == Role.ROLE_ADMIN) {
                catService.CatOperations(new Cat(id), "delete");
                return "Кот удален";
            }

            if (userService.getCurrentUser().getRole() == Role.ROLE_USER) {
                if (catService.CatOperations(new Cat(id), "read").getOwnerID() == userService.getCurrentUser().getId()) {
                    catService.CatOperations(new Cat(id), "delete");
                    return "Кот удален";
                } else {
                    return "Нет доступа к коту";
                }
            }
            return "Неисвестная роль";
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }


    @PutMapping(value = "")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public String update(@RequestBody Cat cat) {
        try {

            if (userService.getCurrentUser().getRole() == Role.ROLE_ADMIN) {
                return ow.writeValueAsString(catService.CatOperations(cat, "update"));
            }

            if (userService.getCurrentUser().getRole() == Role.ROLE_USER) {
                if (catService.CatOperations(cat,"read").getOwnerID() == userService.getCurrentUser().getId()) {
                    return ow.writeValueAsString(catService.CatOperations(cat, "update"));
                } else {
                    return "Нет доступа к коту";
                }
            }

            return "Неисвестная роль";
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }

    @PutMapping(value = "/makeFriend")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public String update(@RequestBody MakeFriendRequest request) {
        try {
            System.out.println(request.id2 );
            var cat = catService.CatOperations(new Cat(request.id1), "read");
            cat.addFriend(request.id2);
            return ow.writeValueAsString(catService.CatOperations(cat, "update"));

        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }


}
