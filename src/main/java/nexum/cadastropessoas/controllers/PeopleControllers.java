package nexum.cadastropessoas.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("")
public class PeopleControllers {

    @GetMapping("")
    public String getAllPeople() {
        return "Hello World";
    }
    
}
