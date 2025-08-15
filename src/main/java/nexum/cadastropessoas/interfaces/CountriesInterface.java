package nexum.cadastropessoas.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import nexum.cadastropessoas.domain.Countries;

@Repository
public interface CountriesInterface extends JpaRepository<Countries, Integer> {}
