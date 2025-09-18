package nexum.cadastropessoas.dao;

import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import nexum.cadastropessoas.domain.People;
import nexum.cadastropessoas.interfaces.PeopleInterfaces;
import nexum.cadastropessoas.services.PeopleServices;
import nexum.cadastropessoas.utils.PeopleUtils;

public class PeopleDAO {

        @Autowired
        public PeopleInterfaces peopleInterfaces;

        public List<People> getAllPeople() {
            return peopleInterfaces.findAll();
        }

        public People getPerson(UUID uuid) {
            return peopleInterfaces.findById(uuid).get();
        }

        public People createPerson(People body) {
            People validPerson = PeopleServices.validatePersonForSave(body);

            peopleInterfaces.save(validPerson);
            return validPerson;
        }

        public People editPerson(UUID uuid, People body) {
            People existPerson = peopleInterfaces.findById(uuid).get();
            People toUpdate = PeopleServices.validatePersonForEdit(existPerson, body);

            peopleInterfaces.save(toUpdate);
            return toUpdate;
        }

        public void deletePerson(UUID uuid) {
            peopleInterfaces.deleteById(uuid);
        }
}
