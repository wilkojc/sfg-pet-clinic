package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class OwnerServiceMapTest {
    OwnerServiceMap ownerServiceMap;

    Long id = 1l;
    String firstName = "Rocket";
    String lastName = "Lugigi";
    @BeforeEach
    void setUp() {
        ownerServiceMap = new OwnerServiceMap(new PetTypeServiceMap(), new PetServiceMap());
        Owner ownerSetUp = Owner.builder().id(id).firstName(firstName).lastName(lastName).build();
        ownerServiceMap.save(ownerSetUp);
    }

    @Test
    void findAll() {
        Set<Owner> ownerSet = ownerServiceMap.findAll();

        assertEquals(1, ownerSet.size());
    }

    @Test
    void findById() {
        assertEquals(firstName, ownerServiceMap.findById(1l).getFirstName());
    }

    @Test
    void saveNoID() {
        Owner savedOwner = ownerServiceMap.save(Owner.builder().build());

        assertNotNull(savedOwner);
        assertNotNull(savedOwner.getId());
    }

    @Test
    void saveExistingID() {
        long id2 = 2l;
        Owner owner2 = Owner.builder().id(id2).build();
        Owner savedOwner = ownerServiceMap.save(owner2);

        assertEquals(id2, savedOwner.getId());
    }

    @Test
    void delete() {
        ownerServiceMap.delete(ownerServiceMap.findById(id));

        assertEquals(0,ownerServiceMap.findAll().size());
    }

    @Test
    void deleteById() {
        ownerServiceMap.deleteById(1l);

        assertEquals(0,ownerServiceMap.findAll().size());
    }

    @Test
    void findByLastName() {
        Owner foundOwner = ownerServiceMap.findByLastName(lastName);

        assertNotNull(foundOwner);

        assertEquals(1l, foundOwner.getId());
    }
}