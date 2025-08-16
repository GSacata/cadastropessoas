package nexum.cadastropessoas.services;

import org.springframework.stereotype.Service;

import nexum.cadastropessoas.controllers.PeopleControllers;

@Service
public class PeopleServices {
    PeopleControllers peopleControllers = new PeopleControllers();
}
