package guru.springframework.sfgpetclinic.bootstrap;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.Vet;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.VetService;
import guru.springframework.sfgpetclinic.services.map.OwnerServiceMap;
import guru.springframework.sfgpetclinic.services.map.VetServiceMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private final OwnerService ownerService;
    private final VetService vetService;

    public DataLoader(OwnerService ownerService, VetService vetService) {
        this.ownerService = new OwnerServiceMap();
        this.vetService = new VetServiceMap();
    }

    @Override
    public void run(String... args) throws Exception {

        Owner owner1 = new Owner();
        owner1.setId(1L);
        owner1.setFirstName("Kacper");
        owner1.setLastName("Wilkojc");

        ownerService.save(owner1);

     Owner owner2 = new Owner();
     owner2.setId(2L);
     owner2.setFirstName("Jan");
     owner2.setLastName("Kowalski");

     ownerService.save(owner2);

     System.out.println("Loaded owners.");


     Vet vet1 = new Vet();
     vet1.setId(1L);
     vet1.setFirstName("Krzysztof");
     vet1.setLastName("Nowak");
     vetService.save(vet1);

     Vet vet2 = new Vet();
     vet2.setId(2L);
     vet2.setFirstName("Alicja");
     vet2.setLastName("Gruz");

     vetService.save(vet2);

     System.out.println("Loaded vets.");
    }
}
