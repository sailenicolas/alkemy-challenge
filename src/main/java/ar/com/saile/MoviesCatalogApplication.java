package ar.com.saile;

import ar.com.saile.domain.*;
import ar.com.saile.repositories.MotionPictureRepository;
import ar.com.saile.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.util.ArrayList;

@SpringBootApplication
public class MoviesCatalogApplication {

    public static void main(String[] args) {

        SpringApplication.run(MoviesCatalogApplication.class, args);
    }
    @Bean
    CommandLineRunner run(MotionPictureRepository motionPictureRepository, UserService userService) {
        return args -> {
            AppUser appUser = new AppUser(null, "john", "password", true, true, true, true, new ArrayList<>());
            AppUser appUser1 = new AppUser(null, "william", "password", true, true, true, true, new ArrayList<>());
            userService.save(appUser);
            userService.save(appUser1);
            Genre genre = new Genre( "Comedia",null);
            Genre genre1 = new Genre( "Dark",null);
            FictionalCharacter fictionalCharacter = new FictionalCharacter("hhol", "yyhhhhhhh hyyy kkjj", 3, 3);

            MotionPicture movie = new MotionPicture("Serie 1", null, LocalDateTime.now(), 1);
            movie.addFictionalCharacters(fictionalCharacter);
            movie.addGenre( genre );

            motionPictureRepository.save(movie);

            MotionPicture serie = new MotionPicture("Games of thrones",null, LocalDateTime.now(), 5);

            serie.addFictionalCharacters(new FictionalCharacter("not", "lol", 1,1));

            serie.addGenre( genre1 );
            serie.addGenre( new Genre("Algo", "") );
            motionPictureRepository.save(serie);

        };
    }

}
