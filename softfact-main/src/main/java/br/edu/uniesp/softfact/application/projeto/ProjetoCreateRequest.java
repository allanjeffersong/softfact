package br.edu.uniesp.softfact.application.projeto;

import br.edu.uniesp.softfact.shared.enums.StatusProjeto;
import br.edu.uniesp.softfact.shared.enums.TipoProjeto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Set;

public record ProjetoCreateRequest(
        @NotBlank String nome,
        String descricao,
        @NotBlank String semestre,
        @NotNull TipoProjeto tipo,
        String nomeEmpresa,
        @NotNull StatusProjeto status,
        Set<Long> stacksDesejadas,
        Set<Long> alunosIds
) {}
