package br.edu.uniesp.softfact.application.mappers;

import br.edu.uniesp.softfact.application.projeto.ProjetoUpdateRequest;
import br.edu.uniesp.softfact.domain.projeto.Projeto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface ProjetoUpdateMapper {

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "stacksDesejadas", ignore = true),
            @Mapping(target = "alunos", ignore = true)
    })
    Projeto toDomain(ProjetoUpdateRequest request);
}
