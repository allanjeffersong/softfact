package br.edu.uniesp.softfact.boundaries.rest.stack;

import br.edu.uniesp.softfact.application.stack.StackResponse;
import br.edu.uniesp.softfact.zo.old.stack.StackTecRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/stacks")
@RequiredArgsConstructor
public class StackQueryController {

    private final StackTecRepository repository;

    @GetMapping
    public Page<StackResponse> listar(Pageable pageable) {
        return repository.findAll(pageable)
                .map(stack -> new StackResponse(stack.getId(), stack.getNome(), stack.getCategoria()));
    }

    @GetMapping("/{id}")
    public StackResponse buscar(@PathVariable Long id) {
        var stack = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Stack não encontrada"));
        return new StackResponse(stack.getId(), stack.getNome(), stack.getCategoria());
    }
}
