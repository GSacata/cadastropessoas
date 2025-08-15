package nexum.cadastropessoas.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import nexum.cadastropessoas.controllers.PeopleControllers;

@Configuration
public class PeopleConfigs {

    @Bean
    PeopleControllers peopleControllers() {
        return new PeopleControllers();
    }
}
