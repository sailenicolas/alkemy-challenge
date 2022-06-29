package ar.com.saile.services;

import ar.com.saile.component.FictionalPathTypeVariable;
import ar.com.saile.domain.FictionalCharacter;
import org.springframework.data.domain.Page;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface FictionalCharacterService {
    Collection<FictionalCharacter> createFictionalByType(FictionalPathTypeVariable typed, FictionalCharacter fictionalCharacter);

    FictionalCharacter updateFictional(Long id, FictionalCharacter fictionalCharacter);

    void deleteById(Long id);

    FictionalCharacter createFictional(FictionalCharacter fictionalCharacter);

    Collection<FictionalCharacter> deleteByMotionPictureIdAndCharacterId(Long idMotionPicture, Long idCharacter);

    FictionalCharacter findById(Long id);

    Page<FictionalCharacter> findAll(int page, Map<String, String> search);
    List<FictionalCharacter> searchAll(int pages, Map<String, String> search);
    Page<FictionalCharacter> findAll(int page, String search);

    Collection<FictionalCharacter> createByMotionPictureIdAndCharacterId(Long idMotionPicture, Long idCharacter);
}
