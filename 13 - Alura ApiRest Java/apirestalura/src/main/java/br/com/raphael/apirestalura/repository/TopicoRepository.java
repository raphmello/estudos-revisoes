package br.com.raphael.apirestalura.repository;

import br.com.raphael.apirestalura.modelo.Topico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TopicoRepository extends JpaRepository<Topico, Long> {
    Page<Topico> findByCursoNome(String nomeCurso, Pageable pageable);  // esta funciona tanto para o atributo cursoNome, quanto para o relacionamento Curso > nome
    List<Topico> findByCurso_Nome(String nomeCurso); // outro tipo de query

    @Query("SELECT t FROM Topico t WHERE t.curso.nome = :nomeCurso")
    List<Topico> carregarPorNomeDoCurso(@Param("nomeCurso") String nomeCurso);
}
