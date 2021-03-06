package ar.com.saile.controllers;

import ar.com.saile.domain.FictionalCharacter;
import ar.com.saile.exceptions.BindingResultException;
import ar.com.saile.services.FictionalCharacterService;
import ar.com.saile.views.Views;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@RestController
@AllArgsConstructor
@RequestMapping(path = "/characters")
public class CharacterController {
    private final FictionalCharacterService fictionalCharacterService;
    @GetMapping(path = "")
    @JsonView(Views.SearchCharacter.class)
    public ResponseEntity<?> searchCharacters(
            @RequestParam(defaultValue = "0", name = "page") int page,
            @RequestParam(defaultValue = "asc", name = "order") String order,
            @RequestParam(defaultValue = "", name = "age", required = false) String age,
            @RequestParam(defaultValue = "", name = "name", required = false) String name,
            @RequestParam(defaultValue = "", name = "movies", required = false) String movies){
        Map<String, String> search = Map.of("order",order, "age",age, "name",name, "movies", movies);
        Page<FictionalCharacter> series = fictionalCharacterService.findAll(page, search);
        return ResponseEntity.ok(series);
    }

    @GetMapping(path = {"/{id}"})
    public ResponseEntity<?> getFictional(@PathVariable Long id) {
        FictionalCharacter fictionalCharacterCollection = fictionalCharacterService.findById(id);
        return ResponseEntity.ok(fictionalCharacterCollection);
    }


    @PostMapping(path = {"/{idCharacter}/movies/{idMotionPicture}/"})
    public ResponseEntity<?> createFictionalByType(@PathVariable Long idMotionPicture, @PathVariable Long idCharacter , HttpServletRequest request) {
        Collection<FictionalCharacter> fictionalCharacterCollection = fictionalCharacterService.createByMotionPictureIdAndCharacterId(idMotionPicture, idCharacter);
        try {
            URI uri = new URI( request.getRequestURI()  );
            return ResponseEntity.created( uri ).body( fictionalCharacterCollection );
        } catch (URISyntaxException e) {
            throw new RuntimeException( e );
        }
    }
    @DeleteMapping(path = {"{idCharacter}/movies/{idMotionPicture}/"})
    public ResponseEntity<?> deleteFictional(@PathVariable Long idMotionPicture, @PathVariable Long idCharacter) {
        Collection<FictionalCharacter> fictionalCharacterCollection = fictionalCharacterService.deleteByMotionPictureIdAndCharacterId(idMotionPicture,idCharacter);

        return ResponseEntity.noContent().build();
    }

    @PutMapping(path = {"/{id}"})
    public ResponseEntity<?> updateFictional(@PathVariable Long id, @Valid @RequestBody FictionalCharacter fictionalCharacter, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            throw new BindingResultException(bindingResult);
        }
        FictionalCharacter updateFictional = fictionalCharacterService.updateFictional(id, fictionalCharacter);
        return ResponseEntity.ok(updateFictional);
    }
    @DeleteMapping(path = {"/{id}"})
    public ResponseEntity<?> deleteFictional(@PathVariable Long id) {
        fictionalCharacterService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    @PostMapping(path = {"/"}, consumes = {APPLICATION_JSON_VALUE})
    public ResponseEntity<?> createFictional(@Valid @RequestBody FictionalCharacter fictionalCharacter, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            throw new BindingResultException(bindingResult);
        }
        FictionalCharacter fictionalCharacter_return = fictionalCharacterService.createFictional(fictionalCharacter);
        return ResponseEntity.ok(fictionalCharacter_return);
    }

}

