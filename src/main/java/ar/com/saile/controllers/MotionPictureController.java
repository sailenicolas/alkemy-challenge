package ar.com.saile.controllers;

import ar.com.saile.domain.MotionPicture;
import ar.com.saile.exceptions.BindingResultException;
import ar.com.saile.services.MotionPictureService;
import ar.com.saile.views.Views;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/movies")
@AllArgsConstructor
public class MotionPictureController {
    private final MotionPictureService motionPictureService;
    @GetMapping(path = "")
    @JsonView(Views.SearchMotionPicture.class)
    public ResponseEntity<?> searchMotionPictures(
            @RequestParam(defaultValue = "0", name = "page") int page,
            @RequestParam(defaultValue = "asc", name = "order") String order,
            @RequestParam(defaultValue = "", name = "genre", required = false) String genre,
            @RequestParam(defaultValue = "", name = "title", required = false) String title){
        Map<String, String> search = Map.of("order",order, "genre",genre, "title",title);
        Page<MotionPicture> series = motionPictureService.findAll(page, search);
        return ResponseEntity.ok(series);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getMotionPicture(@PathVariable Long id){
        return ResponseEntity.ok( motionPictureService.findById(id));
    }
    @PostMapping("/")
    public ResponseEntity<?> createMotionPicture(@Valid @RequestBody final MotionPicture serie, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new BindingResultException(bindingResult);
        }
        return ResponseEntity.ok( motionPictureService.save(serie));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateMotionPicture(@PathVariable Long id, @Valid @RequestBody final MotionPicture serie, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new BindingResultException(bindingResult);
        }

        return ResponseEntity.ok( motionPictureService.updateById(id, serie));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMotionPicture(@PathVariable Long id){
        motionPictureService.delete(id);
        return ResponseEntity.noContent().build();
    }


}
