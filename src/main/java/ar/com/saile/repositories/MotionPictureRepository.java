package ar.com.saile.repositories;

import ar.com.saile.domain.MotionPicture;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface MotionPictureRepository extends PagingAndSortingRepository<MotionPicture, Long>, JpaSpecificationExecutor<MotionPicture> {

}
