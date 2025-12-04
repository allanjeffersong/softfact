package br.edu.uniesp.softfact.infra.projeto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProjetoRepository extends JpaRepository<ProjetoEntity, Long> {

    boolean existsByNomeAndSemestre(String nome, String semestre);

    @Query("SELECT p FROM ProjetoEntity p WHERE " +
            "(:termo IS NULL OR :termo = '' OR LOWER(p.nome) LIKE LOWER(CONCAT('%', :termo, '%')) OR LOWER(p.descricao) LIKE LOWER(CONCAT('%', :termo, '%'))) AND " +
            "(:semestre IS NULL OR :semestre = '' OR p.semestre = :semestre)")
    Page<ProjetoEntity> buscarComFiltros(@Param("termo") String termo, @Param("semestre") String semestre, Pageable pageable);
}
