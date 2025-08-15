package nexum.cadastropessoas.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import nexum.cadastropessoas.domain.People;
import nexum.cadastropessoas.interfaces.PeopleInterfaces;

public class PeopleDAO {

        @Autowired
        public PeopleInterfaces peopleInterfaces;

        public List<People> getAllPeople() {
            return peopleInterfaces.findAll();
        }
}
