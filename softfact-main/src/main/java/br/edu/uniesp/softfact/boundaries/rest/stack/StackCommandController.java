package br.edu.uniesp.softfact.boundaries.rest.stack;

import br.edu.uniesp.softfact.application.stack.StackRequest;
import br.edu.uniesp.softfact.application.stack.StackResponse;
import br.edu.uniesp.softfact.zo.old.stack.StackTecRepository;
import br.edu.uniesp.softfact.zo.old.stack.StackTecnologia;
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
@RequestMapping("/api/stacks")
@RequiredArgsConstructor
public class StackCommandController {

    private final StackTecRepository repository;

    @PostMapping
    public StackResponse criar(@RequestBody @Valid StackRequest request) {
        var stack = StackTecnologia.builder()
                .nome(request.nome())
                .categoria(request.categoria())
                .build();

        stack = repository.save(stack);
        return new StackResponse(stack.getId(), stack.getNome(), stack.getCategoria());
    }

    @PutMapping("/{id}")
    public StackResponse atualizar(@PathVariable Long id, @RequestBody @Valid StackRequest request) {
        var stack = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Stack não encontrada"));

        stack.setNome(request.nome());
        stack.setCategoria(request.categoria());

        stack = repository.save(stack);
        return new StackResponse(stack.getId(), stack.getNome(), stack.getCategoria());
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Stack não encontrada");
        }
        repository.deleteById(id);
    }
}
