package br.edu.uniesp.softfact.application.mappers;

import br.edu.uniesp.softfact.application.aluno.AlunoCreateRequest;
import br.edu.uniesp.softfact.domain.aluno.Aluno;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AlunoCreateMapper {

    Aluno toDomain(AlunoCreateRequest request);
}
