package app;

import com.user.controller.UserController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
//@Import(UserController.class)
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
