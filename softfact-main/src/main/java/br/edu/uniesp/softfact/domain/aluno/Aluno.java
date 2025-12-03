package br.edu.uniesp.softfact.domain.aluno;

import br.edu.uniesp.softfact.shared.enums.Curso;
import br.edu.uniesp.softfact.shared.enums.Periodo;
import br.edu.uniesp.softfact.zo.old.certificado.Certificado;
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
public class Aluno {

    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private Curso curso;
    private String matricula;
    private Periodo periodo;
    private Set<StackTecnologia> stacks = new HashSet<>();
    private Set<Certificado> certificados = new HashSet<>();
}
