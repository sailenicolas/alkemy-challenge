package ar.com.saile;

import ar.com.saile.domain.AppUser;
import ar.com.saile.domain.FictionalCharacter;
import ar.com.saile.domain.Genre;
import ar.com.saile.domain.MotionPicture;
import ar.com.saile.repositories.MotionPictureRepository;
import ar.com.saile.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

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
            FictionalCharacter fictionalCharacter = new FictionalCharacter("Juan Carlos","Juan Carlos es un asalariado sin trabajo",null,  3, 3);

            FictionalCharacter fictionalCharacter1 = new FictionalCharacter("Juan Ismael", "Juan Ismael es un asalariado con trabajo",null, 3, 3);
            MotionPicture movie = new MotionPicture("Serie 1", null, 1);
            movie.addFictionalCharacters(fictionalCharacter);
            movie.addGenre( genre );

            motionPictureRepository.save(movie);

            MotionPicture serie = new MotionPicture("Games of thrones",null,  5);

            serie.addFictionalCharacters(new FictionalCharacter("John", "John es un civil",null, 1,1));
            serie.addFictionalCharacters( fictionalCharacter1 );

            serie.addGenre( genre1 );
            serie.addGenre( new Genre("Algo", "") );
            motionPictureRepository.save(serie);

        };
    }

}
