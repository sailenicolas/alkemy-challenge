package ar.com.saile.component;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import static org.springframework.data.domain.Sort.by;

@Component
public class Utils {

    public static Pageable getPageable(int page, String order) {

        Sort.Direction direction = order.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Sort.Order sort = new Sort.Order(direction,"id");
        System.out.println("order = " + sort);
        Pageable pageRequest = PageRequest.of(page, 5, by(sort));
        return pageRequest;
    }
}
