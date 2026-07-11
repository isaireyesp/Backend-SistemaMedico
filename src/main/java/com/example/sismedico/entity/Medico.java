package com.example.sismedico.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "medicos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    @Column(nullable = false, length = 100)
    private String nombre;

    @NotBlank(message = "El apellido es obligatorio")
    @Column(nullable = false, length = 100)
    private String apellido;

    @Column(nullable = false, unique = true, length = 20)
    private String cedulaProfesional;

    @Column(length = 20)
    private String telefono;

    @Column(nullable = false, unique = true, length = 150)
    private String correo;

    @Column(length = 255)
    private String direccion;

    @Column(length = 255)
    private String foto;

    @Column(length = 1000)
    private String biografia;

    @Column(nullable = false)
    private Integer experiencia;

    @Column(nullable = false)
    private Double costoConsulta;

    private LocalDate fechaNacimiento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "especialidad_id", nullable = false)
    private Especialidad especialidad;

    @Builder.Default
    @OneToMany(
            mappedBy = "medico",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Horario> horarios = new ArrayList<>();

    @Builder.Default
    @OneToMany(
            mappedBy = "medico",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Cita> citas = new ArrayList<>();

    @Column(nullable = false)
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