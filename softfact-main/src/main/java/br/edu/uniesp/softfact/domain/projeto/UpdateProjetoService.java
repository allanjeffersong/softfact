package br.edu.uniesp.softfact.domain.projeto;

import br.edu.uniesp.softfact.application.projeto.ProjetoResponse;

import java.util.Set;

public interface UpdateProjetoService {
    ProjetoResponse criar(Projeto dto, Set<Long> stacksIds, Set<Long> alunosIds);
    ProjetoResponse atualizar(Long id, Projeto dto, Set<Long> stacksIds, Set<Long> alunosIds);
    void excluir(Long id);
}
