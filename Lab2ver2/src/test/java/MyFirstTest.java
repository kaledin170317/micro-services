import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.kaledin170317.Controller.CatController;
import ru.kaledin170317.Controller.OwnerController;
import ru.kaledin170317.Entities.Color;

import java.util.Date;

import static jakarta.persistence.Persistence.createEntityManagerFactory;

public class MyFirstTest {
    private static EntityManagerFactory emf;

    @BeforeEach
    public void initTests(){
        emf = createEntityManagerFactory("TestDataBase");
    }

    @AfterEach
    public void closeTest(){
        emf.close();
    }
    @Test
    public void insertOwnerTest(){

        CatController catController = new CatController(emf);
        OwnerController ownerController = new OwnerController(emf);


        var user1 = ownerController.createOwner("GH",new Date());
        var user2 = ownerController.createOwner("GH2",new Date());
        var user3 = ownerController.createOwner("GH3",new Date());

        var owners = ownerController.getAllOwners();

        assert owners.stream().count() == 3;
        assert ownerController.getOwnerById(user1.getId()).getName() == user1.getName();

    }

    @Test
    public void insertCatTest(){

        CatController catController = new CatController(emf);
        OwnerController ownerController = new OwnerController(emf);


        var user1 = ownerController.createOwner("GH",new Date());
        var user2 = ownerController.createOwner("GH2",new Date());
        var user3 = ownerController.createOwner("GH3",new Date());

        var cat1 = catController.createCat("Ma", new Date(), user1, "Puma", Color.RED);
        var cat2 = catController.createCat("Ma", new Date(), user2, "Puma", Color.RED);

        var cats = catController.getAllCats();

        assert cats.stream().count() == 2;
        assert catController.getCatById(cat1.getId()).getOwner().getName() == user1.getName();
    }

    @Test
    public void insertFriendShip(){

        CatController catController = new CatController(emf);
        OwnerController ownerController = new OwnerController(emf);


        var user1 = ownerController.createOwner("GH",new Date());
        var user2 = ownerController.createOwner("GH2",new Date());
        var user3 = ownerController.createOwner("GH3",new Date());

        var cat1 = catController.createCat("Ma", new Date(), user1, "Puma", Color.RED);
        var cat2 = catController.createCat("Ma", new Date(), user2, "Puma", Color.RED);

        catController.MakeFriendShip(cat1, cat2);

        var cat1fromDataBase = catController.getCatById(cat1.getId());

        assert cat1fromDataBase.getFriends().stream().count() == 0;
    }
}
