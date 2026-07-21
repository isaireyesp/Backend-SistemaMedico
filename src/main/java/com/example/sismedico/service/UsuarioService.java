package com.example.sismedico.service;

import com.example.sismedico.dto.request.UsuarioRequest;
import com.example.sismedico.entity.Rol;
import com.example.sismedico.entity.Usuario;
import com.example.sismedico.repository.RolRepository;
import com.example.sismedico.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;
    private final PasswordEncoder passwordEncoder;


    /**
     * Registrar usuario
     */
    public Usuario registrarUsuario(UsuarioRequest request) {


        if (usuarioRepository.existsByCorreo(request.getCorreo())) {
            throw new RuntimeException(
                    "El correo ya está registrado."
            );
        }


        Rol rol = rolRepository.findById(request.getRolId())
                .orElseThrow(() ->
                        new RuntimeException(
                                "Rol no encontrado."
                        ));


        Usuario usuario = Usuario.builder()
                .nombre(request.getNombre())
                .apellido(request.getApellido())
                .correo(request.getCorreo())
                .password(
                        passwordEncoder.encode(
                                request.getPassword()
                        )
                )
                .telefono(request.getTelefono())
                .direccion(request.getDireccion())
                .foto(request.getFoto())
                .genero(request.getGenero())
                .fechaNacimiento(request.getFechaNacimiento())
                .rol(rol)
                .activo(request.getActivo())
                .emailVerificado(request.getEmailVerificado())
                .tokenFirebase(request.getTokenFirebase())
                .build();


        return usuarioRepository.save(usuario);

    }


    /**
     * Listar usuarios
     */
    @Transactional(readOnly = true)
    public List<Usuario> listarUsuarios() {

        return usuarioRepository.findAll();

    }


    /**
     * Obtener usuario por ID
     */
    @Transactional(readOnly = true)
    public Usuario obtenerPorId(Long id) {

        return usuarioRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Usuario no encontrado."
                        ));

    }


    