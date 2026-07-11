package com.example.sismedico.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pacientes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false, unique = true)
    private Usuario usuario;

    @Column(length = 5)
    private String tipoSangre;

    @Column(length = 200)
    private String alergias;

    @Column(length = 500)
    private String enfermedadesCronicas;

    @Column(length = 500)
    private String medicamentos;

    @Column(length = 255)
    private String contactoEmergencia;

    @Column(length = 20)
    private String telefonoEmergencia;

    private Double peso;

    private Double altura;

    private LocalDate fechaNacimiento;

    @Builder.Default
    @OneToMany(
            mappedBy = "paciente",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Cita> citas = new ArrayList<>();

    @Column(nullable = false)
    @Builder.Default
    private Boolean activo = true;

    @Column(nullable = false, updatable = false)
    private LocalDateTime fechaRegistro;

    @PrePersist
    public void prePersist() {

        fechaRegistro = LocalDateTime.now();

        if (activo == null) {
            activo = true;
        }

    }

}