package org.example.person_management;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

/**
 * @author HuZili
 */
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@MapperScan({ "org.example.person_management.**.mapper","**.mapper" })
public class PersonManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(PersonManagementApplication.class, args);
    }

}
