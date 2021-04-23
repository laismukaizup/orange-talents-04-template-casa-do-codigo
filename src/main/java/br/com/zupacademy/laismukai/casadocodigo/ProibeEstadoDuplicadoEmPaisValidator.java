package br.com.zupacademy.laismukai.casadocodigo;

import br.com.zupacademy.laismukai.casadocodigo.criaEstado.Estado;
import br.com.zupacademy.laismukai.casadocodigo.criaEstado.EstadoRepository;
import br.com.zupacademy.laismukai.casadocodigo.criaEstado.EstadoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
public class ProibeEstadoDuplicadoEmPaisValidator implements Validator {

    @Autowired
    EstadoRepository estadoRepository;


    @Override
    public boolean supports(Class<?> clazz) {
        return EstadoRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if(errors.hasErrors())
            return;

        EstadoRequest estadoRequest = (EstadoRequest) target;

        Optional<Estado> estado = estadoRepository.findByPaisId(estadoRequest.getIdPais());
        if(estado.isEmpty())
            errors.rejectValue("idPais", "",
                    "Não existe um pais cadastrado com o id " +estadoRequest.getIdPais());

        estado = estadoRepository.findByNomeAndPaisId(estadoRequest.getNome(), estadoRequest.getIdPais());
        if(estado.isPresent())
            errors.rejectValue("nome", "", "Já existe um estado " + estadoRequest.getNome()
                    + " para o país " + estado.get().getPais().getNome());

    }
}
