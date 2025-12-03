package br.edu.uniesp.softfact.infra.aluno;

import br.edu.uniesp.softfact.application.aluno.AlunoResponse;
import br.edu.uniesp.softfact.domain.aluno.Aluno;
import br.edu.uniesp.softfact.domain.aluno.UpdateAlunoService;
import br.edu.uniesp.softfact.infra.mapper.AlunoEntityMapper;
import br.edu.uniesp.softfact.zo.old.stack.StackTecRepository;
import br.edu.uniesp.softfact.zo.old.stack.StackTecnologia;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class UpdateAlunoServiceImpl implements UpdateAlunoService {

    private final AlunoRepository repo;
    private final StackTecRepository stackRepo;
    private final AlunoEntityMapper entityMapper;

    @Override
    public AlunoResponse criar(Aluno dto) {
        if (repo.existsByEmail(dto.getEmail())) {
            throw new DataIntegrityViolationException("E-mail já cadastrado.");
        }
        return entityMapper.toResponse(repo.save(entityMapper.toEntity(dto)));
    }

    @Override
    public AlunoResponse atualizar(Long id, Aluno dto) {
        AlunoEntity existente = repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Aluno não encontrado: " + id));

        if (!existente.getEmail().equalsIgnoreCase(dto.getEmail()) && repo.existsByEmail(dto.getEmail())) {
            throw new DataIntegrityViolationException("E-mail já cadastrado.");
        }
        if (!existente.getMatricula().equalsIgnoreCase(dto.getMatricula()) && repo.existsByMatricula(dto.getMatricula())) {
            throw new DataIntegrityViolationException("Matrícula já cadastrada.");
        }

        existente.setNome(dto.getNome());
        existente.setEmail(dto.getEmail());
        existente.setTelefone(dto.getTelefone());
        existente.setCurso(dto.getCurso());
        existente.setMatricula(dto.getMatricula());
        existente.setPeriodo(dto.getPeriodo());
        existente.setStacks(buscarStacks(dto.getStacks().stream().map(StackTecnologia::getId).collect(Collectors.toSet())));

        return entityMapper.toResponse(existente);
    }

    @Override
    public void excluir(Long id) {
        if (!repo.existsById(id)) throw new EntityNotFoundException("Aluno não encontrado: " + id);
        repo.deleteById(id);
    }

    private Set<StackTecnologia> buscarStacks(Set<Long> ids) {
        if (ids == null || ids.isEmpty()) return Set.of();
        return ids.stream()
                .map(id -> stackRepo.findById(id)
                        .orElseThrow(() -> new EntityNotFoundException("Stack não encontrada: " + id)))
                .collect(Collectors.toSet());
    }
}
