package br.barbertech.gestao.service;

import br.barbertech.gestao.entity.Proprietario;
import br.barbertech.gestao.exception.RnException;
import br.barbertech.gestao.repository.ProprietarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class ProprietarioService {

    private final ProprietarioRepository repository;

    @Transactional
    public Proprietario salvar(Proprietario proprietario) {
        boolean cpfExiste = repository.findByCpfUsuario(proprietario.getCpfUsuario()).
                filter(p -> !p.equals(proprietario)).isPresent();

        if (cpfExiste) {
            throw new RnException("CPF já cadastrado no sistema!");
        }

        return repository.save(proprietario);
    }

    @Transactional
    public void excluir(Long idItemCaixa) {
        repository.deleteById(idItemCaixa);
    }

}
