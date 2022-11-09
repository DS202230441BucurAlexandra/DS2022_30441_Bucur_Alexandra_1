package com.alexandra.assignment;

import com.alexandra.assignment.model.User;
import com.alexandra.assignment.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

import java.util.TimeZone;

@SpringBootApplication
public class AssignmentApplication extends SpringBootServletInitializer {

    @Bean
    public CommandLineRunner run(UserService userService) {
        return args -> {
            if (userService.getAdminUser() == null) {
                userService.saveAdmin(new User(1 ,  "admin", "admin@yahoo.com","admin", 1));
            }
        };
    }

    public static void main(String[] args) {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        SpringApplication.run(AssignmentApplication.class, args);
    }
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(AssignmentApplication.class);
    }

}
