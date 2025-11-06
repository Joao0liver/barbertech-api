package br.barbertech.gestao.repository;

import br.barbertech.gestao.entity.Servico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ServicoRepository extends JpaRepository<Servico, Long> {

    @Query("SELECT s FROM Servico s WHERE s.nomeServico LIKE CONCAT('%', :nome, '%')")
    List<Servico> findByNomeServico(@Param("nome") String nome);

}