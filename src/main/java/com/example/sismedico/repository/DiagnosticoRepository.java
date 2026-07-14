package com.example.sismedico.repository;

import com.example.sismedico.entity.Diagnostico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiagnosticoRepository extends JpaRepository<Diagnostico, Long> {

}