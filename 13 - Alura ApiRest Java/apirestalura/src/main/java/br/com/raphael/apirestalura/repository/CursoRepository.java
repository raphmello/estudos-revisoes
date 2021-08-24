package br.com.raphael.apirestalura.repository;

import br.com.raphael.apirestalura.modelo.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, Long> {
    Curso findByNome(String nomeCurso);
}
