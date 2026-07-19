package com.example.sismedico.service;

import com.example.sismedico.dto.request.HorarioRequest;
import com.example.sismedico.entity.Horario;
import com.example.sismedico.entity.Medico;
import com.example.sismedico.repository.HorarioRepository;
import com.example.sismedico.repository.MedicoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class HorarioService {


    private final HorarioRepository horarioRepository;

    private final MedicoRepository medicoRepository;



    /**
     * Crear horario
     */
    public Horario crearHorario(HorarioRequest request) {


        Medico medico = medicoRepository.findById(
                        request.getMedicoId())
                .orElseThrow(() ->
                        new RuntimeException(
                                "Médico no encontrado"));


        if (!medico.getActivo()) {

            throw new RuntimeException(
                    "El médico está inactivo");

        }


        Horario horario = Horario.builder()
                .medico(medico)
                .diaSemana(request.getDiaSemana())
                .horaInicio(request.getHoraInicio())
                .horaFin(request.getHoraFin())
                .activo(true)
                .build();


        return horarioRepository.save(horario);

    }



    /**
     * Listar horarios
     */
    @Transactional(readOnly = true)
    public List<Horario> listarHorarios() {


        return horarioRepository.findAll();

    }



    /**
     * Obtener horario por ID
     */
    @Transactional(readOnly = true)
    public Horario obtenerPorId(Long id) {


        return horarioRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Horario no encontrado"));

    }



    /**
     * Actualizar horario
     */
    public Horario actualizarHorario(
            Long id,
            HorarioRequest request) {


        Horario horario =
                horarioRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Horario no encontrado"));



        Medico medico =
                medicoRepository.findById(
                                request.getMedicoId())
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Médico no encontrado"));



        horario.setMedico(medico);

        horario.setDiaSemana(
                request.getDiaSemana()
        );

        horario.setHoraInicio(
                request.getHoraInicio()
        );

        horario.setHoraFin(
                request.getHoraFin()
        );


        return horarioRepository.save(horario);

    }



    /**
     * Eliminar horario
     */
    public void eliminarHorario(Long id) {


        Horario horario =
                horarioRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Horario no encontrado"));


        horarioRepository.delete(horario);

    }



    /**
     * Obtener horarios de un médico
     */
    @Transactional(readOnly = true)
    public List<Horario> listarPorMedico(Long medicoId) {


        Medico medico =
                medicoRepository.findById(medicoId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Médico no encontrado"));


        return horarioRepository.findByMedico(medico);

    }

}