package br.com.zupacademy.laismukai.casadocodigo.criaAutor;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ProibeEmailDuplicadoValidator implements Validator {

	@Autowired
	AutorRepository autorRepository;

	@Override
	public boolean supports(Class<?> clazz) {

		return AutorRequest.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		if (errors.hasErrors()) {
			return;
		}

		AutorRequest request = (AutorRequest) target;
		Optional<Autor> possivelAutor = autorRepository.findByEmail(request.getEmail());
		if (possivelAutor.isPresent())
			errors.rejectValue("email", null, "Já existe um(a) autor(a) com o mesmo e-mail " + request.getEmail());

	}

}
