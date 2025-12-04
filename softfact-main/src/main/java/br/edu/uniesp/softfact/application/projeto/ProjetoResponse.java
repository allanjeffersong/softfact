package br.edu.uniesp.softfact.application.projeto;

import br.edu.uniesp.softfact.application.aluno.AlunoResponse;
import br.edu.uniesp.softfact.shared.enums.StatusProjeto;
import br.edu.uniesp.softfact.shared.enums.TipoProjeto;
import br.edu.uniesp.softfact.zo.old.stack.dto.StackResumo;

import java.util.Set;

public record ProjetoResponse(
        Long id,
        String nome,
        String descricao,
        String semestre,
        TipoProjeto tipo,
        String nomeEmpresa,
        StatusProjeto status,
        Set<StackResumo> stacksDesejadas,
        Set<AlunoResponse> alunos
) {}
