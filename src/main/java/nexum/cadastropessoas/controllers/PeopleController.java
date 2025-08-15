package nexum.cadastropessoas.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import nexum.cadastropessoas.dao.PeopleDAO;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("")
public class PeopleController {

    @Autowired public PeopleDAO peopleDao;

    @GetMapping("")
    public String getAllPeople() {
        return "Hello World";
    }
    
}
