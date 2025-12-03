package br.edu.uniesp.softfact.application.aluno;

import br.edu.uniesp.softfact.shared.enums.Curso;
import br.edu.uniesp.softfact.shared.enums.Periodo;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Set;

public record AlunoCreateRequest(
        @NotBlank String nome,
        @NotBlank @Email String email,
        String telefone,
        @NotNull Curso curso,
        @NotBlank String matricula,
        @NotNull Periodo periodo,
        Set<Long> stacksIds
) {}
