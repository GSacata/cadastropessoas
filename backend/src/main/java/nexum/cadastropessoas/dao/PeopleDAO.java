package nexum.cadastropessoas.dao;

import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import nexum.cadastropessoas.domain.People;
import nexum.cadastropessoas.interfaces.PeopleInterfaces;
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
            String pName = body.getName();
            String pDoc = body.getDocument();
            String pPhone = body.getPhone();
            String pEmail = body.getEmail();

            Boolean validDoc = false;

            String cleanDoc = PeopleUtils.onlyDigits(pDoc);
            if (cleanDoc.length() == 11) {
                validDoc = PeopleUtils.CPFValidator(pDoc);
            } else if (cleanDoc.length() == 14) {
                validDoc = PeopleUtils.CNPJValidator(pDoc);
            } else {
                throw new IllegalArgumentException("Invalid length for document");
            }

            People person = new People();
            person.setName(pName);

            if (validDoc) {
                person.setDocument(pDoc);;
            } else {
                throw new IllegalArgumentException("Invalid document");
            }
            
            if (Objects.nonNull(pPhone) && pPhone.length() > 0) {
                Boolean validPhone = PeopleUtils.PhoneValidator(pPhone);
                if (validPhone) {
                    person.setPhone(pPhone);
                } else {
                    throw new IllegalArgumentException("Invalid phone number");
                }
            }

            if (Objects.nonNull(pEmail) && pEmail.length() > 0) {
                Boolean validEmail = PeopleUtils.EmailValidator(pEmail);

                if (validEmail) {
                    person.setEmail(pEmail);
                } else {
                    throw new IllegalArgumentException("Invalid email address");
                }
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

            String pDoc = body.getDocument();
            if (pDoc.length() > 0) {
                Boolean validDoc = false;
                
                String cleanDoc = PeopleUtils.onlyDigits(pDoc);
                if (cleanDoc.length() == 11) {
                    validDoc = PeopleUtils.CPFValidator(pDoc);
                } else if (cleanDoc.length() == 14) {
                    validDoc = PeopleUtils.CNPJValidator(pDoc);
                } else {
                    throw new IllegalArgumentException("Invalid length for document");
                }

                if (validDoc) {
                    toUpdate.setDocument(pDoc);;
                } else {
                    throw new IllegalArgumentException("Invalid document");
                }
            }

            String pPhone = body.getPhone();
            if (Objects.nonNull(pPhone) && pPhone.length() > 0) {
                Boolean validPhone = PeopleUtils.PhoneValidator(pPhone);
                if (validPhone) {
                    toUpdate.setPhone(pPhone);
                } else {
                    throw new IllegalArgumentException("Invalid phone number");
                }
            }

            String pEmail = body.getEmail();
            if (Objects.nonNull(pEmail) && pEmail.length() > 0) {
                Boolean validEmail = PeopleUtils.EmailValidator(pEmail);

                if (validEmail) {
                    toUpdate.setEmail(pEmail);
                } else {
                    throw new IllegalArgumentException("Invalid email address");
                }
            }

            toUpdate.setLastUpdated(Instant.now());

            peopleInterfaces.save(toUpdate);
            return toUpdate;
        }

        public void deletePerson(UUID uuid) {
            peopleInterfaces.deleteById(uuid);
        }
}
