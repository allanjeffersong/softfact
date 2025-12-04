package br.edu.uniesp.softfact.infra.mapper;

import br.edu.uniesp.softfact.application.aluno.AlunoResponse;
import br.edu.uniesp.softfact.domain.aluno.Aluno;
import br.edu.uniesp.softfact.infra.aluno.AlunoEntity;
import br.edu.uniesp.softfact.zo.old.stack.StackTecnologia;
import br.edu.uniesp.softfact.zo.old.stack.dto.StackResumo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface AlunoEntityMapper {

    @Mappings({
            @Mapping(target = "stacks", ignore = true),
            @Mapping(target = "certificados", ignore = true)
    })
    AlunoEntity toEntity(Aluno dto);

    @Mappings({
            @Mapping(target = "stacks", ignore = true),
            @Mapping(target = "certificados", ignore = true)
    })
    Aluno toDomain(AlunoEntity entity);

    @Mapping(target = "stacks", source = "stacks", qualifiedByName = "mapStacks")
    AlunoResponse toResponse(AlunoEntity entity);

    @Named("mapStacks")
    default Set<StackResumo> mapStacks(Set<StackTecnologia> stacks) {
        if (stacks == null) return Set.of();
        return stacks.stream()
                .map(s -> new StackResumo(s.getId(), s.getNome(), s.getCategoria()))
                .collect(Collectors.toSet());
    }
}
