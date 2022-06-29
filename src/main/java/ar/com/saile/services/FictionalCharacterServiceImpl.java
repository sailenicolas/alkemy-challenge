package ar.com.saile.services;

import ar.com.saile.component.FictionalPathTypeVariable;
import ar.com.saile.component.Utils;
import ar.com.saile.domain.FictionalCharacter;
import ar.com.saile.exceptions.NotDeletedException;
import ar.com.saile.repositories.CharacterRepository;
import ar.com.saile.repositories.MotionPictureRepository;
import ar.com.saile.exceptions.RecordNotFoundException;
import ar.com.saile.specs.FictionalCharacterSpecs;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class FictionalCharacterServiceImpl implements FictionalCharacterService {
    private final MotionPictureRepository motionPictureRepository;
    private final CharacterRepository characterRepository;

    @Override
    public Collection<FictionalCharacter> CreateByMotioPictureIdAndCharacterId(FictionalPathTypeVariable fictionalPathTypeVariable, Long id, Long idCharacter) throws RecordNotFoundException {
        Collection<FictionalCharacter> fictionalCharacters = new ArrayList<>();
        switch (fictionalPathTypeVariable) {
            case MOVIES, SERIES -> fictionalCharacters = motionPictureRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("NOT FOUND")).getFictionalCharacters();
        }
        return fictionalCharacters;
    }

    @Override
    public Collection<FictionalCharacter> createFictionalByType(FictionalPathTypeVariable typed, FictionalCharacter fictionalCharacter) {

        return null;
    }

    @Override
    public FictionalCharacter updateFictional(Long id, FictionalCharacter fictionalCharacter) {

        return null;
    }

    @Override
    public void deleteById(Long id) {
        characterRepository.deleteById(id);
        boolean exists = characterRepository.existsById(id);
        if(exists)
            throw new NotDeletedException("Not deleted");
    }

    @Override
    public FictionalCharacter createFictional(FictionalCharacter fictionalCharacter) {

        return characterRepository.save(fictionalCharacter);
    }

    @Override
    public Collection<FictionalCharacter> deleteByMotionPictureIdAndCharacterId(FictionalPathTypeVariable fictionalPathTypeVariable, Long idMotionPicture, Long idCharacter) {

        return null;
    }

    @Override
    public FictionalCharacter findById(Long id) {

        return characterRepository.findById(id).orElseThrow(()->new RecordNotFoundException("NOT FOUND"));
    }

    @Override
    public Page<FictionalCharacter> findAll(int page, Map<String, String> search) {

        Pageable pageable = Utils.getPageable( page, search.get( "order" ) );
        return characterRepository.findAll(new FictionalCharacterSpecs(search ), pageable );
    }

    @Override
    public List<FictionalCharacter> searchAll(int pages, Map<String, String> search) {
        return characterRepository.findAll(new FictionalCharacterSpecs(search ) );
    }

    @Override
    public Page<FictionalCharacter> findAll(int page, String order) {
        Pageable pageable = Utils.getPageable( page, order );
        return characterRepository.findAll(pageable);
    }
}
