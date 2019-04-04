package app;

import com.user.controller.UserController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
//@Import(UserController.class)
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
