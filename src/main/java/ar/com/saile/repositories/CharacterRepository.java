package ar.com.saile.repositories;

import ar.com.saile.domain.FictionalCharacter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface CharacterRepository extends JpaRepository<FictionalCharacter, Long> {

}
