package nexum.cadastropessoas.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import nexum.cadastropessoas.dao.CountriesDAO;
import nexum.cadastropessoas.dao.PeopleDAO;
// import nexum.cadastropessoas.domain.Countries;
import nexum.cadastropessoas.domain.People;
import nexum.cadastropessoas.dto.PeopleDTO;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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
    public List<PeopleDTO> getAllPeople() {
        List<People> peopleList = peopleDAO.getAllPeople();
        
        return peopleList.stream()
        .map(this::convertToDTO).collect(Collectors.toList());
    }

    @GetMapping("/{uuid}")
    public PeopleDTO getPerson(@PathVariable UUID uuid) {
        People person = peopleDAO.getPerson(uuid);
        return this.convertToDTO(person);
    }

    @PostMapping("/create")
    public PeopleDTO createPerson(@RequestBody People body) {
        People person = peopleDAO.createPerson(body);
        return this.convertToDTO(person);
    }

    @PutMapping("/{uuid}/edit")
    public PeopleDTO editPerson(@PathVariable UUID uuid, @RequestBody People body) {
        People person = peopleDAO.editPerson(uuid, body);
        return this.convertToDTO(person);
    }

    @DeleteMapping("/{uuid}/delete")
    public void deletePerson(@PathVariable UUID uuid) {
        peopleDAO.deletePerson(uuid);
    }

    private PeopleDTO convertToDTO(People person) {
        PeopleDTO peopleDto = new PeopleDTO();
        
        peopleDto.setUuid(person.getUuid());
        peopleDto.setName(person.getName());
        peopleDto.setDocument(person.getDocument());
        peopleDto.setEmail(person.getEmail());
        peopleDto.setPhone(person.getPhone());
        peopleDto.setLastUpdated(person.getLastUpdated());
        
        return peopleDto;
    }
    

    // @GetMapping("/countries")
    // public List<Countries> getMethodName() {
    //     return countriesDAO.getAllCountries();
    // }
    
    
}
