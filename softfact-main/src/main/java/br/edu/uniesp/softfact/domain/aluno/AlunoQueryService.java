package br.edu.uniesp.softfact.domain.aluno;

import br.edu.uniesp.softfact.application.aluno.AlunoResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AlunoQueryService {
    AlunoResponse buscarPorId(Long id);
    Page<AlunoResponse> listar(String termo, Pageable pageable);
}
