package guru.springframework.sfgpetclinic.services.map;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import guru.springframework.sfgpetclinic.model.Pet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PetMapServiceTest {

    private PetMapService petMapService;

    private Long petId = 1L;

    @BeforeEach
    void setUp() {
        petMapService = new PetMapService();

        Pet pet = new Pet();
        pet.setId(petId);
        petMapService.save(pet);
    }

    @Test
    void findAll() {
        Set<Pet> pets = petMapService.findAll();

        assertEquals(1, pets.size());
    }

    @Test
    void findById() {
        Pet pet = petMapService.findById(petId);

        assertNotNull(pet);
        assertNotNull(pet.getId());
        assertEquals(petId, pet.getId());
    }

    @Test
    void saveExistingId() {
        Long petId = 2L;
        Pet pet = new Pet();
        pet.setId(petId);

        Pet savedPet = petMapService.save(pet);

        assertNotNull(savedPet);
        assertNotNull(savedPet.getId());
        assertEquals(petId, savedPet.getId());
    }

    @Test
    void saveNoId() {
        Pet savedPet = petMapService.save(new Pet());

        assertNotNull(savedPet);
        assertNotNull(savedPet.getId());
    }

    @Test
    void delete() {
        petMapService.delete(petMapService.findById(petId));

        assertEquals(0, petMapService.findAll().size());
    }

    @Test
    void deleteById() {
        petMapService.deleteById(petId);

        assertEquals(0, petMapService.findAll().size());
    }
}
