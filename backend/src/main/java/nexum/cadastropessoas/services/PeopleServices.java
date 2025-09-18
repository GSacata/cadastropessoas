package nexum.cadastropessoas.services;

import java.time.Instant;
import java.util.Objects;

import org.springframework.stereotype.Service;

import nexum.cadastropessoas.controllers.PeopleControllers;
import nexum.cadastropessoas.domain.People;
import nexum.cadastropessoas.utils.PeopleUtils;

@Service
public class PeopleServices {
    PeopleControllers peopleControllers = new PeopleControllers();

    public static People validatePersonForSave(People personBody) {
        String pName = personBody.getName();
        String pDoc = personBody.getDocument();
        String pPhone = personBody.getPhone();
        String pEmail = personBody.getEmail();

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

        return person;
    }

    public static People validatePersonForEdit(People existentPersonBody, People newPersonBody) {
        existentPersonBody.setName(newPersonBody.getName());

        String pDoc = newPersonBody.getDocument();
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
                existentPersonBody.setDocument(pDoc);;
            } else {
                throw new IllegalArgumentException("Invalid document");
            }
        }

        String pPhone = newPersonBody.getPhone();
        if (Objects.nonNull(pPhone) && pPhone.length() > 0) {
            Boolean validPhone = PeopleUtils.PhoneValidator(pPhone);
            if (validPhone) {
                existentPersonBody.setPhone(pPhone);
            } else {
                throw new IllegalArgumentException("Invalid phone number");
            }
        }

        String pEmail = newPersonBody.getEmail();
        if (Objects.nonNull(pEmail) && pEmail.length() > 0) {
            Boolean validEmail = PeopleUtils.EmailValidator(pEmail);

            if (validEmail) {
                existentPersonBody.setEmail(pEmail);
            } else {
                throw new IllegalArgumentException("Invalid email address");
            }
        }

        existentPersonBody.setLastUpdated(Instant.now());

        return existentPersonBody;
    }
}
