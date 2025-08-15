package nexum.cadastropessoas.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import nexum.cadastropessoas.domain.Countries;
import nexum.cadastropessoas.interfaces.CountriesInterface;

public class CountriesDAO {

    @Autowired
    public CountriesInterface countriesInterface;

    public List<Countries> getAllCountries() {
        return countriesInterface.findAll();
    }
}
