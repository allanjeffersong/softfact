package br.edu.uniesp.softfact.domain.projeto;

import br.edu.uniesp.softfact.domain.aluno.Aluno;
import br.edu.uniesp.softfact.shared.enums.StatusProjeto;
import br.edu.uniesp.softfact.shared.enums.TipoProjeto;
import br.edu.uniesp.softfact.zo.old.stack.StackTecnologia;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class Projeto {

    private Long id;
    private String nome;
    private String descricao;
    private String semestre;
    private TipoProjeto tipo;
    private String nomeEmpresa;
    private StatusProjeto status;
    private Set<StackTecnologia> stacksDesejadas = new HashSet<>();
    private Set<Aluno> alunos = new HashSet<>();
}
