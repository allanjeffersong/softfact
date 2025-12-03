package br.edu.uniesp.softfact.infra.aluno;

import br.edu.uniesp.softfact.shared.enums.Curso;
import br.edu.uniesp.softfact.shared.enums.Periodo;
import br.edu.uniesp.softfact.zo.old.certificado.Certificado;
import br.edu.uniesp.softfact.zo.old.stack.StackTecnologia;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter @Setter
@Entity
@Table(name = "tb_softfact_aluno",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_aluno_email", columnNames = "email"),
                @UniqueConstraint(name = "uk_aluno_matricula", columnNames = "matricula")
        })
@NoArgsConstructor @AllArgsConstructor @Builder
public class AlunoEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String nome;

    @Email
    @Column(nullable = false)
    private String email;

    private String telefone;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Curso curso;

    @NotBlank
    @Column(nullable = false)
    private String matricula;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Periodo periodo;

    @ManyToMany
    @JoinTable(name = "tb_softfact_aluno_stack",
            joinColumns = @JoinColumn(name = "aluno_id"),
            inverseJoinColumns = @JoinColumn(name = "stack_id"))
    private Set<StackTecnologia> stacks = new HashSet<>();

    @OneToMany(mappedBy = "aluno", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Certificado> certificados = new HashSet<>();
}
