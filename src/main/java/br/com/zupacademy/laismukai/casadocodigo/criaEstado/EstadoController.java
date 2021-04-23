package br.com.zupacademy.laismukai.casadocodigo.criaEstado;

import br.com.zupacademy.laismukai.casadocodigo.criaPais.PaisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/estado")
public class EstadoController {

    @PersistenceContext
    EntityManager manager;
    @Autowired
    PaisRepository paisRepository;

    @PostMapping
    @Transactional
    public String cadastar(@RequestBody @Valid EstadoRequest estadoRequest)
    {
       Estado estado = estadoRequest.converter(paisRepository);
       manager.persist(estado);
       return estado.toString();
    }
}
