package br.edu.uniesp.softfact.domain.aluno;

import br.edu.uniesp.softfact.application.aluno.AlunoResponse;

public interface UpdateAlunoService {
    AlunoResponse criar(Aluno domain);
    AlunoResponse atualizar(Long id, Aluno domain);
    void excluir(Long id);
}
