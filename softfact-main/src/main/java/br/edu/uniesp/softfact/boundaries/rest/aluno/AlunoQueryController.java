package br.edu.uniesp.softfact.boundaries.rest.aluno;

import br.edu.uniesp.softfact.application.aluno.AlunoResponse;
import br.edu.uniesp.softfact.domain.aluno.AlunoQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/api/alunos")
@RequiredArgsConstructor
public class AlunoQueryController {

    private final AlunoQueryService service;

    private static final Set<String> ALLOWED_SORT_FIELDS = Set.of(
            "id", "nome", "email", "matricula", "curso", "periodo"
    );

    @GetMapping("/{id}")
    public AlunoResponse buscar(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @GetMapping
    public Page<AlunoResponse> listar(
            @RequestParam(required = false) String query,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "nome") String sort,
            @RequestParam(defaultValue = "ASC") Sort.Direction direction) {

        String sortField = ALLOWED_SORT_FIELDS.contains(sort) ? sort : "nome";

        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortField));
        return service.listar(query, pageable);
    }
}
