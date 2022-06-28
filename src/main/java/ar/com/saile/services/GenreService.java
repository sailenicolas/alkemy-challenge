package ar.com.saile.services;

import ar.com.saile.domain.Genre;
import org.springframework.data.domain.Page;

public interface GenreService {

    Page<Genre> findAll(int page, String order);

    Genre findById(Long id);

    Genre updateMovie(Long id, Genre genre);

    void deleteById(Long id);

    Genre createMovie(Genre genre);

    Genre save(Genre genre1);
}
