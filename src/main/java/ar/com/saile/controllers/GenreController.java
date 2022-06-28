package ar.com.saile.controllers;

import ar.com.saile.domain.Genre;
import ar.com.saile.exceptions.BindingResultException;
import ar.com.saile.services.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
@RequestMapping("/genres")
public class GenreController {
    private final GenreService genreService;
    @GetMapping(path = "/")
    public ResponseEntity<?> getGenres(@RequestParam(defaultValue = "0", name = "page", required = false) int page,
                                       @RequestParam(defaultValue = "asc", name = "order", required = false) String order){
        Page<Genre> genres = genreService.findAll(page, order);
        return ResponseEntity.ok(genres);
    }

    @GetMapping(path = {"/{id}"})
    public ResponseEntity<?> getGenre(@PathVariable Long id) {
        Genre genre = genreService.findById(id);
        return ResponseEntity.ok(genre);
    }

    @PutMapping(path = {"/{id}"})
    public ResponseEntity<?> updateGenre(@PathVariable Long id, @Valid @RequestBody Genre genre, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            throw new BindingResultException(bindingResult);
        }
        Genre genre1 = genreService.updateMovie(id, genre);
        return ResponseEntity.ok(genre1);
    }

    @DeleteMapping(path = {"/{id}"})
    public ResponseEntity<?> deleteGenre(@PathVariable Long id) {
        genreService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(path = {"/"}, consumes = {APPLICATION_JSON_VALUE})
    public ResponseEntity<?> createGenre(@Valid @RequestBody Genre genre, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            throw new BindingResultException(bindingResult);
        }
        Genre genre1 = genreService.createMovie(genre);
        return ResponseEntity.ok(genre1);
    }
}
