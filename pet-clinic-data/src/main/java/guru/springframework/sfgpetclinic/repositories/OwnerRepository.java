package guru.springframework.sfgpetclinic.repositories;

import guru.springframework.sfgpetclinic.model.Owner;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface OwnerRepository extends CrudRepository<Owner, Long> {
   //  Set<Owner> findByFirstName();
}
