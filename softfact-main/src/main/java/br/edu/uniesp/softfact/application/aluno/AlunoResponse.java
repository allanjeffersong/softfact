package br.edu.uniesp.softfact.application.aluno;

import br.edu.uniesp.softfact.shared.enums.Curso;
import br.edu.uniesp.softfact.shared.enums.Periodo;
import br.edu.uniesp.softfact.zo.old.stack.dto.StackResumo;

import java.util.Set;

public record AlunoResponse(
        Long id,
        String nome,
        String email,
        String telefone,
        Curso curso,
        String matricula,
        Periodo periodo,
        Set<StackResumo> stacks
) {}
