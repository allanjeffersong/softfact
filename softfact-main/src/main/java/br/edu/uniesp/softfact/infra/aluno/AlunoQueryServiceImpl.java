package br.edu.uniesp.softfact.infra.aluno;

import br.edu.uniesp.softfact.application.aluno.AlunoResponse;
import br.edu.uniesp.softfact.domain.aluno.AlunoQueryService;
import br.edu.uniesp.softfact.zo.old.stack.dto.StackResumo;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class AlunoQueryServiceImpl implements AlunoQueryService {

    private final AlunoRepository repo;

    @Transactional(readOnly = true)
    @Override
    public AlunoResponse buscarPorId(Long id) {
        return repo.findById(id).map(this::toResponse)
                .orElseThrow(() -> new EntityNotFoundException("Aluno não encontrado: " + id));
    }

    @Transactional(readOnly = true)
    @Override
    public Page<AlunoResponse> listar(String termo, Pageable pageable) {
        Page<AlunoEntity> page;
        if (termo == null || termo.isBlank()) {
            page = repo.findAll(pageable);
        } else {
            ExampleMatcher matcher = ExampleMatcher.matchingAny()
                    .withIgnoreNullValues()
                    .withMatcher("nome", m -> m.contains().ignoreCase())
                    .withMatcher("email", m -> m.contains().ignoreCase())
                    .withMatcher("matricula", m -> m.contains().ignoreCase());
            AlunoEntity probe = new AlunoEntity();
            probe.setNome(termo);
            probe.setEmail(termo);
            probe.setMatricula(termo);
            page = repo.findAll(Example.of(probe, matcher), pageable);
        }
        return page.map(this::toResponse);
    }

    private AlunoResponse toResponse(AlunoEntity a) {
        return new AlunoResponse(
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
        );
    }
}
