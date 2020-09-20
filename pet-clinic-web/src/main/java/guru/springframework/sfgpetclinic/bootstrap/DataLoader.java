package guru.springframework.sfgpetclinic.bootstrap;

import guru.springframework.sfgpetclinic.model.*;
import guru.springframework.sfgpetclinic.services.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialtyService specialtyService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService, SpecialtyService specialtyService, PetService petService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialtyService = specialtyService;
    }

    @Override
    public void run(String... args) throws Exception {

        int count = petTypeService.findAll().size();

        if (count == 0) {
            loadData();
        }
    }

    private void loadData() {
        PetType dog = new PetType();
        dog.setName("Dog");
        PetType savedDogPetType = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("Cat");
        PetType savedCatPetType = petTypeService.save(cat);

        Owner owner1 = new Owner();
        owner1.setFirstName("Kacper");
        owner1.setLastName("Wilkojc");
        owner1.setAddress("Domowa");
        owner1.setCity("Plock");
        owner1.setTelephone("123456789");

        Pet owner1Pet = new Pet();
        owner1Pet.setName("Mike");
        owner1Pet.setPetType(dog);
        owner1Pet.setBirthDate(LocalDate.now());
        owner1Pet.setOwner(owner1);
        owner1.getPets().add(owner1Pet);

        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Jan");
        owner2.setLastName("Kowalski");
        owner2.setAddress("Domowa");
        owner2.setCity("Plock");
        owner2.setTelephone("987123645");

        Pet owner2Pet = new Pet();
        owner2Pet.setName("Fiona");
        owner2Pet.setPetType(cat);
        owner2Pet.setBirthDate(LocalDate.now());
        owner2Pet.setOwner(owner2);
        owner2.getPets().add(owner2Pet);

        ownerService.save(owner2);

        System.out.println("Loaded owners.");

        Specialty radiology = new Specialty();
        radiology.setDescription("Radiology");
        Specialty savedRadiology = specialtyService.save(radiology);
        Specialty dentistry = new Specialty();
        dentistry.setDescription("Dentistry");
        Specialty savedDentistry = specialtyService.save(dentistry);
        Specialty surgery = new Specialty();
        surgery.setDescription("Surgery");
        Specialty savedSurgery = specialtyService.save(surgery);

        Vet vet1 = new Vet();
        vet1.setFirstName("Krzysztof");
        vet1.setLastName("Nowak");
        vet1.getSpecialties().addAll(Arrays.asList(savedDentistry, savedRadiology));

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Alicja");
        vet2.setLastName("Gruz");
        vet2.getSpecialties().add(savedSurgery);

        vetService.save(vet2);

        System.out.println("Loaded vets.");
    }
}
