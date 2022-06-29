package ar.com.saile.services;

import ar.com.saile.domain.MotionPicture;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface MotionPictureService {

    Page<MotionPicture> findAll(Integer page, String sort);
    List<MotionPicture> findAll(Integer page, Map<String, String> search);

    MotionPicture findById(Long id);

    MotionPicture save(MotionPicture serie);

    void delete(Long id);

    MotionPicture updateById(Long id, MotionPicture serie);
}
