package ar.com.saile.controllers;

import ar.com.saile.component.FictionalPathTypeVariable;
import ar.com.saile.domain.FictionalCharacter;
import ar.com.saile.exceptions.BindingResultException;
import ar.com.saile.services.FictionalCharacterService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Collection;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@RestController
@AllArgsConstructor
@RequestMapping(path = "/")
public class CharacterController {
    private final FictionalCharacterService fictionalCharacterService;
    @GetMapping(path = {"/characters/{id}"})
    public ResponseEntity<?> getFictional(@PathVariable Long id) {
        FictionalCharacter fictionalCharacterCollection = fictionalCharacterService.findById(id);
        return ResponseEntity.ok(fictionalCharacterCollection);
    }


    @PostMapping(path = {"/{fictionalPathTypeVariable}/{idMotionPicture}/characters/{idCharacter}"})
    public ResponseEntity<?> createFictionalByType(@PathVariable FictionalPathTypeVariable fictionalPathTypeVariable, @PathVariable Long idMotionPicture,@PathVariable Long idCharacter ,HttpServletResponse response) {
        Collection<FictionalCharacter> fictionalCharacterCollection = fictionalCharacterService.CreateByMotioPictureIdAndCharacterId(fictionalPathTypeVariable, idMotionPicture, idCharacter);
        return ResponseEntity.ok(fictionalCharacterCollection);
    }
    @DeleteMapping(path = {"/{fictionalPathTypeVariable}/{idMotionPicture}/characters/{idCharacter}"})
    public ResponseEntity<?> deleteFictional(@PathVariable FictionalPathTypeVariable fictionalPathTypeVariable, @PathVariable Long idMotionPicture, @PathVariable Long idCharacter) {
        Collection<FictionalCharacter> fictionalCharacterCollection = fictionalCharacterService.deleteByMotionPictureIdAndCharacterId(fictionalPathTypeVariable, idMotionPicture,idCharacter);

        return ResponseEntity.noContent().build();
    }

    @PutMapping(path = {"/characters/{id}"})
    public ResponseEntity<?> updateFictional(@PathVariable Long id, @Valid @RequestBody FictionalCharacter fictionalCharacter, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            throw new BindingResultException(bindingResult);
        }
        FictionalCharacter fictionalCharacterCollection = fictionalCharacterService.updateFictional(id, fictionalCharacter);
        return ResponseEntity.ok(fictionalCharacterCollection);
    }
    @DeleteMapping(path = {"/characters/{id}"})
    public ResponseEntity<?> deleteFictional(@PathVariable Long id) {
        fictionalCharacterService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    @PostMapping(path = {"/characters/"}, consumes = {APPLICATION_JSON_VALUE})
    public ResponseEntity<?> createFictional(@Valid @RequestBody FictionalCharacter fictionalCharacter, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            throw new BindingResultException(bindingResult);
        }
        FictionalCharacter fictionalCharacter_return = fictionalCharacterService.createFictional(fictionalCharacter);
        return ResponseEntity.ok(fictionalCharacter_return);
    }

}

