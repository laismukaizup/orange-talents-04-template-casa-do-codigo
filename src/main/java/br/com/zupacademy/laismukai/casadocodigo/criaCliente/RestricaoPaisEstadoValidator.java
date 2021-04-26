package br.com.zupacademy.laismukai.casadocodigo.criaCliente;

import br.com.zupacademy.laismukai.casadocodigo.criaEstado.Estado;
import br.com.zupacademy.laismukai.casadocodigo.criaEstado.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.List;
import java.util.Optional;

@Component
public class RestricaoPaisEstadoValidator implements Validator {

    @Autowired
    EstadoRepository estadoRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return ClienteRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if(errors.hasErrors())
            return;

        ClienteRequest clienteRequest = (ClienteRequest) target;
        Long idPais = clienteRequest.getIdPais();
        Long idEstado = clienteRequest.getIdEstado();
        //verifica se estado é valido
        if (idEstado != null) { // se existe estado. verifica se ele é do pais selecionado
            Optional<Estado> estado = estadoRepository.findById(idEstado);
            if(estado.isEmpty())
                errors.rejectValue("idEstado", "",
                        "Estado id " + idEstado + " não existe no bando de dados.");
            else {
                //se  estado existe, verificar se ele é do pais selecionado.
                if (!estado.get().getPais().getId().equals(idPais)) {
                    errors.rejectValue("idEstado", "",
                            "Não existe o estado " + idEstado + " para o país " + idPais);
                }
            }

        } else {
            //se não tiver estado selecionado, verificar se pais tem estados.

            List<Estado> estadosDoPais = estadoRepository.findByPaisId(idPais);
            if (estadosDoPais.size() > 0) {
                errors.rejectValue("idPais", "",
                        "existe estados cadastrado para este pais. Selecione um");
            }
        }

    }
}
