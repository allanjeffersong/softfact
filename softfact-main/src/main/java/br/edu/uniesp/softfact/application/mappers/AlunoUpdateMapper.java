package br.edu.uniesp.softfact.application.mappers;

import br.edu.uniesp.softfact.application.aluno.AlunoUpdateRequest;
import br.edu.uniesp.softfact.domain.aluno.Aluno;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AlunoUpdateMapper {

    Aluno toDomain(AlunoUpdateRequest request);
}
