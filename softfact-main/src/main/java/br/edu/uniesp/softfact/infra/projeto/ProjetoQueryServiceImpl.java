package br.edu.uniesp.softfact.infra.projeto;

import br.edu.uniesp.softfact.application.aluno.AlunoResponse;
import br.edu.uniesp.softfact.application.projeto.ProjetoResponse;
import br.edu.uniesp.softfact.domain.projeto.ProjetoQueryService;
import br.edu.uniesp.softfact.zo.old.stack.dto.StackResumo;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProjetoQueryServiceImpl implements ProjetoQueryService {

    private final ProjetoRepository repo;

    @Override
    public ProjetoResponse buscarPorId(Long id) {
        return repo.findById(id).map(this::toResponse)
                .orElseThrow(() -> new EntityNotFoundException("Projeto não encontrado: " + id));
    }

    @Override
    public Page<ProjetoResponse> listar(String termo, String semestre, Pageable pageable) {
        Page<ProjetoEntity> page = repo.buscarComFiltros(termo, semestre, pageable);
        return page.map(this::toResponse);
    }

    private ProjetoResponse toResponse(ProjetoEntity p) {
        return new ProjetoResponse(
                p.getId(),
                p.getNome(),
                p.getDescricao(),
                p.getSemestre(),
                p.getTipo(),
                p.getNomeEmpresa(),
                p.getStatus(),
                p.getStacksDesejadas().stream()
                        .map(s -> new StackResumo(s.getId(), s.getNome(), s.getCategoria()))
                        .collect(Collectors.toSet()),
                p.getAlunos().stream()
                        .map(a -> new AlunoResponse(
                                a.getId(),
                                a.getNome(),
                                a.getEmail(),
                                a.getTelefone(),
                                a.getCurso(),
                                a.getMatricula(),
                                a.getPeriodo(),
                                a.getStacks().stream()
                                        .map(s -> new StackResumo(s.getId(), s.getNome(), s.getCategoria()))
                                        .collect(Collectors.toSet())
                        ))
                        .collect(Collectors.toSet())
        );
    }
}
