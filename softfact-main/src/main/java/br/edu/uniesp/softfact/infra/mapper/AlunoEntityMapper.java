package br.edu.uniesp.softfact.infra.mapper;

import br.edu.uniesp.softfact.application.aluno.AlunoResponse;
import br.edu.uniesp.softfact.domain.aluno.Aluno;
import br.edu.uniesp.softfact.infra.aluno.AlunoEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AlunoEntityMapper {

    AlunoEntity toEntity(Aluno dto);

    AlunoResponse toResponse(AlunoEntity entity);
}
