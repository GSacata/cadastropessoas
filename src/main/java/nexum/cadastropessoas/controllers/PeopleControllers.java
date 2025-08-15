package nexum.cadastropessoas.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import nexum.cadastropessoas.dao.CountriesDAO;
// import nexum.cadastropessoas.domain.Countries;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("")
public class PeopleControllers {

    @Autowired
    public CountriesDAO countriesDAO;

    @GetMapping("")
    public String getAllPeople() {
        return "Hello World";
    }

    // @GetMapping("/countries")
    // public List<Countries> getMethodName() {
    //     return countriesDAO.getAllCountries();
    // }
    
    
}
