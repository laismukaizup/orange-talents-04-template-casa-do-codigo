package br.com.zupacademy.laismukai.casadocodigo.criaEstado;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface EstadoRepository extends CrudRepository<Estado, Long> {

    Optional<Estado> findByNomeAndPaisId(String nome, Long idPais);

    Optional<Estado> findByPaisId(Long idPais);
}
