package com.raphael.spring.data.repository;

import com.raphael.spring.data.orm.Cargo;
import com.raphael.spring.data.orm.Funcionario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FuncionarioRepository extends CrudRepository<Funcionario, Integer> {
}
