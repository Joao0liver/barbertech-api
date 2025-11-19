package br.barbertech.gestao.repository;

import br.barbertech.gestao.entity.Proprietario;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProprietarioRepository extends JpaRepository<Proprietario, Long> {

    List<Proprietario> findByNomeUsuarioContaining(String nomeUsuario);

    Optional<Proprietario> findByCpfUsuario(String cpfUsuario);

}
