package nexum.cadastropessoas.interfaces;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import nexum.cadastropessoas.domain.People;

@Repository
public interface PeopleInterfaces extends JpaRepository<People, UUID> {}
