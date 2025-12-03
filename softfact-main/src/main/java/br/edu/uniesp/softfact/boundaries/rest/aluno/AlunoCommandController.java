package br.edu.uniesp.softfact.boundaries.rest.aluno;

import br.edu.uniesp.softfact.application.aluno.AlunoCreateRequest;
import br.edu.uniesp.softfact.application.aluno.AlunoResponse;
import br.edu.uniesp.softfact.application.aluno.AlunoUpdateRequest;
import br.edu.uniesp.softfact.application.mappers.AlunoCreateMapper;
import br.edu.uniesp.softfact.application.mappers.AlunoUpdateMapper;
import br.edu.uniesp.softfact.domain.aluno.UpdateAlunoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/alunos")
@RequiredArgsConstructor
public class AlunoCommandController {

    private final UpdateAlunoService service;
    private final AlunoCreateMapper createMapper;
    private final AlunoUpdateMapper updateMapper;

    @PostMapping
    public AlunoResponse criar(@RequestBody @Valid AlunoCreateRequest request) {
        var dominio = createMapper.toDomain(request);
        return service.criar(dominio);
    }

    @PutMapping("/{id}")
    public AlunoResponse atualizar(@PathVariable Long id, @RequestBody @Valid AlunoUpdateRequest request) {
        var dominio = updateMapper.toDomain(request);
        return service.atualizar(id, dominio);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id) {
        service.excluir(id);
    }
}
