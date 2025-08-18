package nexum.cadastropessoas.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import nexum.cadastropessoas.dao.CountriesDAO;
import nexum.cadastropessoas.dao.PeopleDAO;
// import nexum.cadastropessoas.domain.Countries;
import nexum.cadastropessoas.domain.People;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/api")
public class PeopleControllers {

    // @Autowired public CountriesDAO countriesDAO;
    @Autowired public PeopleDAO peopleDAO;

    @GetMapping("")
    public List<People> getAllPeople() {
        return peopleDAO.getAllPeople();
    }

    @GetMapping("/{uuid}")
    public People getPerson(@PathVariable UUID uuid) {
        return peopleDAO.getPerson(uuid);
    }

    @PostMapping("/create")
    public People createPerson(@RequestBody People body) {
        return peopleDAO.createPerson(body);
    }

    @PutMapping("/{uuid}/edit")
    public People editPerson(@PathVariable UUID uuid, @RequestBody People body) {
        return peopleDAO.editPerson(uuid, body);
    }

    @DeleteMapping("/{uuid}/delete")
    public void deletePerson(@PathVariable UUID uuid) {
        peopleDAO.deletePerson(uuid);
    }
    

    // @GetMapping("/countries")
    // public List<Countries> getMethodName() {
    //     return countriesDAO.getAllCountries();
    // }
    
    
}
