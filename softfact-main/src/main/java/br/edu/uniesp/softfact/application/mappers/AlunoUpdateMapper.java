package br.edu.uniesp.softfact.application.mappers;

import br.edu.uniesp.softfact.application.aluno.AlunoUpdateRequest;
import br.edu.uniesp.softfact.domain.aluno.Aluno;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface AlunoUpdateMapper {

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "stacks", ignore = true),
            @Mapping(target = "certificados", ignore = true)
    })
    Aluno toDomain(AlunoUpdateRequest request);
}
