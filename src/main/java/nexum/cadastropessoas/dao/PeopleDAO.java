package nexum.cadastropessoas.dao;

import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import nexum.cadastropessoas.domain.People;
import nexum.cadastropessoas.interfaces.PeopleInterfaces;

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
            String pName = body.getName();
            String pDoc = body.getDocument();
            String pPhone = body.getPhone();
            String pEmail = body.getEmail();

            People person = new People(pName, pDoc);
            
            if (Objects.nonNull(pPhone)) {
                person.setPhone(pPhone);
            }
            if (Objects.nonNull(pEmail)) {
                person.setEmail(pEmail);
            }
            
            Instant now = Instant.now();
            person.setCreated(now);
            person.setLastUpdated(now);

            peopleInterfaces.save(person);
            return person;
        }

        public People editPerson(UUID uuid, People body) {
            People toUpdate = peopleInterfaces.findById(uuid).get();

            toUpdate.setName(body.getName());
            toUpdate.setDocument(body.getDocument());
            toUpdate.setEmail(body.getEmail());
            toUpdate.setPhone(body.getPhone());
            toUpdate.setLastUpdated(Instant.now());

            peopleInterfaces.save(toUpdate);
            return toUpdate;
        }

        public void deletePerson(UUID uuid) {
            peopleInterfaces.deleteById(uuid);
        }
}
