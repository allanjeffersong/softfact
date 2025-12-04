package br.edu.uniesp.softfact.infra.mapper;

import br.edu.uniesp.softfact.application.aluno.AlunoResponse;
import br.edu.uniesp.softfact.application.projeto.ProjetoResponse;
import br.edu.uniesp.softfact.domain.projeto.Projeto;
import br.edu.uniesp.softfact.infra.aluno.AlunoEntity;
import br.edu.uniesp.softfact.infra.projeto.ProjetoEntity;
import br.edu.uniesp.softfact.zo.old.stack.StackTecnologia;
import br.edu.uniesp.softfact.zo.old.stack.dto.StackResumo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface ProjetoEntityMapper {

    @Mappings({
            @Mapping(target = "stacksDesejadas", ignore = true),
            @Mapping(target = "alunos", ignore = true)
    })
    ProjetoEntity toEntity(Projeto dto);

    @Mappings({
            @Mapping(target = "stacksDesejadas", ignore = true),
            @Mapping(target = "alunos", ignore = true)
    })
    Projeto toDomain(ProjetoEntity entity);

    @Mappings({
            @Mapping(target = "stacksDesejadas", source = "stacksDesejadas", qualifiedByName = "mapStacks"),
            @Mapping(target = "alunos", source = "alunos", qualifiedByName = "mapAlunos")
    })
    ProjetoResponse toResponse(ProjetoEntity entity);

    @Named("mapStacks")
    default Set<StackResumo> mapStacks(Set<StackTecnologia> stacks) {
        if (stacks == null) return Set.of();
        return stacks.stream()
                .map(s -> new StackResumo(s.getId(), s.getNome(), s.getCategoria()))
                .collect(Collectors.toSet());
    }

    @Named("mapAlunos")
    default Set<AlunoResponse> mapAlunos(Set<AlunoEntity> alunos) {
        if (alunos == null) return Set.of();
        return alunos.stream()
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
                .collect(Collectors.toSet());
    }
}
