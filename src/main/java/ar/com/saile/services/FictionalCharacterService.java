package ar.com.saile.services;

import ar.com.saile.component.FictionalPathTypeVariable;
import ar.com.saile.domain.FictionalCharacter;
import ar.com.saile.exceptions.RecordNotFoundException;

import java.util.Collection;

public interface FictionalCharacterService {
    Collection<FictionalCharacter> CreateByMotioPictureIdAndCharacterId(FictionalPathTypeVariable typed, Long id, Long idCharacter) throws RecordNotFoundException;

    Collection<FictionalCharacter> createFictionalByType(FictionalPathTypeVariable typed, FictionalCharacter fictionalCharacter);

    FictionalCharacter updateFictional(Long id, FictionalCharacter fictionalCharacter);

    void deleteById(Long id);

    FictionalCharacter createFictional(FictionalCharacter fictionalCharacter);

    Collection<FictionalCharacter> deleteByMotionPictureIdAndCharacterId(FictionalPathTypeVariable fictionalPathTypeVariable, Long idMotionPicture, Long idCharacter);

    FictionalCharacter findById(Long id);
}
