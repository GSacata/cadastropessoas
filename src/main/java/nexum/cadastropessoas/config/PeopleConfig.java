package nexum.cadastropessoas.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import nexum.cadastropessoas.dao.PeopleDAO;

@Configuration
public class PeopleConfig {

    @Bean
    PeopleDAO peopleDao() {
        return new PeopleDAO();
    }
}
