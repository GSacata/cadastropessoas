package nexum.cadastropessoas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import nexum.cadastropessoas.dev.DevTesting;

@SpringBootApplication
public class CadastropessoasApplication {

	public static void main(String[] args) {
		SpringApplication.run(CadastropessoasApplication.class, args);

		DevTesting devTesting = new DevTesting();
		devTesting.testThis();
	}

}
