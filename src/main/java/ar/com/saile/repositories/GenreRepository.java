package ar.com.saile.repositories;

import ar.com.saile.domain.Genre;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

public interface GenreRepository extends PagingAndSortingRepository<Genre, Long> {

}
