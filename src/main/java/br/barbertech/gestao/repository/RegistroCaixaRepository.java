package br.barbertech.gestao.repository;

import br.barbertech.gestao.entity.RegistroCaixa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;
import java.util.List;

@Repository
public interface RegistroCaixaRepository extends JpaRepository<RegistroCaixa, Long> {

}
