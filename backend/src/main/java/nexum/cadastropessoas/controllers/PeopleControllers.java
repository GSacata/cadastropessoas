package nexum.cadastropessoas.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import nexum.cadastropessoas.dao.PeopleDAO;
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

    @PostMapping("/quick-start")
    public void quickStartPeople() {
        // USED FOR DEV PURPOSES ONLY

        People person1 = new People();
        person1.setName("Teste da Silva");
        person1.setDocument("163.870.900-91");
        person1.setEmail("testesilva.silva@email.com");
        person1.setPhone("99988-5566");
        this.peopleDAO.createPerson(person1);
        
        People person2 = new People();
        person2.setName("Teste dos Santos de Oliveira");
        person2.setDocument("86548838041");
        person2.setEmail("teste.san.ol@meuemail.com");
        person2.setPhone("+55 14 93355-8844");
        this.peopleDAO.createPerson(person2);

        People person3 = new People();
        person3.setName("Doce Vida Confeitaria");
        person3.setDocument("68.512.526/0001-94");
        person3.setEmail("docevida@dominio.com.br");
        person3.setPhone("0800 222 6464");
        this.peopleDAO.createPerson(person3);

        People person4 = new People();
        person4.setName("Osório & Horácio Advogados Associados");
        person4.setDocument("81.798.616/0001-51");
        person4.setEmail("oso.hor.aa@meusite.com");
        person4.setPhone("21 3232-5511");
        this.peopleDAO.createPerson(person4);
    }
}
