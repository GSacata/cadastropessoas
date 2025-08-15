package nexum.cadastropessoas.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import nexum.cadastropessoas.domain.People;
import nexum.cadastropessoas.interfaces.PeopleInterface;

public class PeopleDAO {

    @Autowired public PeopleInterface peopleInterface;

    public List<People> allPeople() {
        return peopleInterface.findAll();
    }
}
