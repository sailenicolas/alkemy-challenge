package ar.com.saile.services;

import ar.com.saile.component.Utils;
import ar.com.saile.domain.Genre;
import ar.com.saile.repositories.GenreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;
    @Override
    public Page<Genre> findAll(int page, String order) {
        Pageable pageRequest = Utils.getPageable(page, order);
        return genreRepository.findAll(pageRequest);
    }

    @Override
    public Genre findById(Long id) {

        return genreRepository.findById(id).orElseThrow(()->new RecordNotFoundException("NOT FOUND"));
    }

    @Override
    public Genre updateMovie(Long id, Genre genre) {
        Genre updateGenre = this.findById( id );
        updateGenre.setImage(genre.getImage());
        updateGenre.setName(genre.getName());
        return this.save(updateGenre);
    }

    @Override
    public void deleteById(Long id) {
        genreRepository.deleteById(id);
        if(genreRepository.existsById(id))
            throw new NotDeletedException("NOT DELETED");
    }

    @Override
    public Genre createMovie(Genre genre) {

        return this.save(genre);
    }

    @Override
    public Genre save(Genre genre) {

        return genreRepository.save(genre);
    }
}
