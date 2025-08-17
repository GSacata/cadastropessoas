package nexum.cadastropessoas.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import nexum.cadastropessoas.dao.PeopleDAO;

@Configuration
public class PeopleConfigs {

    @Bean
    PeopleDAO peopleDAO() {
        return new PeopleDAO();
    }

    // @Bean
    // CountriesDAO countriesDAO() {
    //     return new CountriesDAO();
    // }
}
