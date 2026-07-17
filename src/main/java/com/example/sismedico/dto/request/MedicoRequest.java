package com.example.sismedico.dto.request;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MedicoRequest {

    @NotNull(message = "El usuario es obligatorio")
    private Long usuarioId;

    @NotNull(message = "La especialidad es obligatoria")
    private Long especialidadId;

    @NotBlank(message = "El número de cédula profesional es obligatorio")
    @Size(max = 30, message = "La cédula no puede superar los 30 caracteres")
    private String cedulaProfesional;

    @NotBlank(message = "El consultorio es obligatorio")
    @Size(max = 100)
    private String consultorio;

    @DecimalMin(value = "0.0", inclusive = true, message = "El costo debe ser mayor o igual a 0")
    private Double costoConsulta;

    @Min(value = 10, message = "La duración mínima es de 10 minutos")
    @Max(value = 180, message = "La duración máxima es de 180 minutos")
    private Integer duracionConsulta;

    @Size(max = 1000, message = "La biografía no puede exceder los 1000 caracteres")
    private String biografia;

    @Builder.Default
    private Boolean activo = true;

}