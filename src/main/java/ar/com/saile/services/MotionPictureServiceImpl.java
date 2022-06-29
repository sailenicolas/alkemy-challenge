package ar.com.saile.services;

import ar.com.saile.component.Utils;
import ar.com.saile.domain.MotionPicture;
import ar.com.saile.exceptions.NotDeletedException;
import ar.com.saile.exceptions.RecordNotFoundException;
import ar.com.saile.repositories.MotionPictureRepository;
import ar.com.saile.specs.MotionPictureSpecs;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MotionPictureServiceImpl implements MotionPictureService {
    private final MotionPictureRepository motionPictureRepository;

    @Override
    public Page<MotionPicture> findAll(Integer page, String order) {
        Pageable pageRequest = Utils.getPageable(page, order);
        return motionPictureRepository.findAll(pageRequest);
    }

    @Override
    public List<MotionPicture> findAll(Integer page, Map<String, String> search) {
        return motionPictureRepository.findAll(new MotionPictureSpecs( search ));
    }

    @Override
    public MotionPicture findById(Long id) {

        return motionPictureRepository.findById(id).orElseThrow(()->new RecordNotFoundException("NOT FOUND"));
    }

    @Override
    public MotionPicture save(MotionPicture serie) {

        return motionPictureRepository.save(serie);
    }

    @Override
    public void delete(Long id) {
        MotionPicture motionPicture = this.findById(id);
        motionPictureRepository.delete(motionPicture);
        boolean exists = motionPictureRepository.existsById(motionPicture.getId());
        if (exists)
            throw new NotDeletedException("NOT DELETED");

    }

    @Override
    public MotionPicture updateById(Long id, MotionPicture motionPicture) {
        MotionPicture motionPictureNew = this.findById(id);
        motionPictureNew.setImage(motionPicture.getImage());
        motionPictureNew.setRating(motionPicture.getRating());
        motionPictureNew.setTitle(motionPicture.getTitle());

        return this.save(motionPictureNew);
    }
}
