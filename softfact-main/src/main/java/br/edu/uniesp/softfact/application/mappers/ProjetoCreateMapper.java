package br.edu.uniesp.softfact.application.mappers;

import br.edu.uniesp.softfact.application.projeto.ProjetoCreateRequest;
import br.edu.uniesp.softfact.domain.projeto.Projeto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface ProjetoCreateMapper {

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "stacksDesejadas", ignore = true),
            @Mapping(target = "alunos", ignore = true)
    })
    Projeto toDomain(ProjetoCreateRequest request);
}
