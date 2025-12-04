package br.edu.uniesp.softfact.domain.aluno;

import br.edu.uniesp.softfact.application.aluno.AlunoResponse;

import java.util.Set;

public interface UpdateAlunoService {
    AlunoResponse criar(Aluno domain, Set<Long> stacksIds);
    AlunoResponse atualizar(Long id, Aluno domain, Set<Long> stacksIds);
    void excluir(Long id);
}
