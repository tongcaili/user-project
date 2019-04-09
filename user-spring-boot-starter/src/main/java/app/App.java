package app;

import com.user.controller.UserController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.context.annotation.Import;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
//@Import(UserController.class)
@Slf4j
public class App implements CommandLineRunner {
    @Autowired
    private DataSourceProperties dataSourceProperties;

    public static void main(String[] args) {

        SpringApplication.run(App.class, args);

    }

    @Override
    public void run(String... args) throws Exception {
        log.info(dataSourceProperties.getUrl());
    }
}
