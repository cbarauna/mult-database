package com.mult.database.multdatabase.db1.repository;

import com.mult.database.multdatabase.db1.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PessoaRepository extends JpaRepository<Pessoa, UUID> {
}
