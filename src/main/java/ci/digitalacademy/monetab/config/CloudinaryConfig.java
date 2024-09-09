package ci.digitalacademy.monetab.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.cloudinary.*;
import io.github.cdimascio.dotenv.Dotenv;



@Configuration
public class CloudinaryConfig {
    /*@Bean
    public Cloudinary cloudinary() {
        Dotenv dotenv = Dotenv.load();
        Cloudinary cloudinary = new Cloudinary(dotenv.get("CLOUDINARY_URL"));
        cloudinary.config.secure = true;
        return cloudinary;
    }*/
}
