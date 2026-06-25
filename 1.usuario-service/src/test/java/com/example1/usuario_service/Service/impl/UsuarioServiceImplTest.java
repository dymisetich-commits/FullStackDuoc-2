package com.example1.usuario_service.Service.impl;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example1.usuario_service.DTO.LoginDTO;
import com.example1.usuario_service.DTO.UsuarioRequestDTO;
import com.example1.usuario_service.DTO.UsuarioResponseDTO;
import com.example1.usuario_service.Model.UsuarioModel;
import com.example1.usuario_service.Repository.UsuarioRepository;
import com.example1.usuario_service.Security.JwtUtil;
import com.example1.usuario_service.Service.Impl.UsuarioServiceImpl;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceImplTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private JwtUtil jwtUtil;

    @InjectMocks
    private UsuarioServiceImpl usuarioService; // 🔥 CORREGIDO

    @Test
    void obtenerUsuarioPorId() {

        UsuarioModel usuario = new UsuarioModel();
        usuario.setId(1L);
        usuario.setNombre("Patricio");
        usuario.setCorreo("patricio@gmail.com");
        usuario.setRol("CLIENTE");

        when(usuarioRepository.findById(1L))
                .thenReturn(Optional.of(usuario));

        UsuarioResponseDTO resultado =
                usuarioService.obtenerPorId(1L); // 🔥 CORRECTO

        assertEquals("Patricio", resultado.getNombre());
        assertEquals("CLIENTE", resultado.getRol());
    }

    @Test
    void crearUsuarioDebeGuardarCorrectamente() {

        UsuarioRequestDTO dto = new UsuarioRequestDTO();
        dto.setNombre("Patricio");
        dto.setCorreo("pato@gmail.com");
        dto.setPassword("1234");

        UsuarioModel usuarioGuardado = new UsuarioModel();
        usuarioGuardado.setId(1L);
        usuarioGuardado.setNombre("Patricio");
        usuarioGuardado.setCorreo("pato@gmail.com");
        usuarioGuardado.setRol("CLIENTE");

        when(usuarioRepository.save(any()))
                .thenReturn(usuarioGuardado);

        UsuarioResponseDTO resultado =
                usuarioService.crearUsuario(dto); // 🔥 CORRECTO

        assertEquals("Patricio", resultado.getNombre());

        verify(usuarioRepository).save(any());
    }

    @Test
    void loginDebeRetornarToken() {

        LoginDTO dto = new LoginDTO();
        dto.setCorreo("pato@gmail.com");
        dto.setPassword("1234");

        UsuarioModel usuario = new UsuarioModel();
        usuario.setCorreo("pato@gmail.com");
        usuario.setPassword("1234");

        when(usuarioRepository.findByCorreo("pato@gmail.com"))
                .thenReturn(Optional.of(usuario));

        when(jwtUtil.generarToken("pato@gmail.com"))
                .thenReturn("TOKEN123");

        String token = usuarioService.login(dto); // 🔥 CORRECTO

        assertEquals("TOKEN123", token);
    }

    @Test
    void obtenerTodosDebeRetornarLista() {

        UsuarioModel usuario1 = new UsuarioModel();
        usuario1.setId(1L);
        usuario1.setNombre("Patricio");
        usuario1.setCorreo("pato@gmail.com");
        usuario1.setRol("CLIENTE");

        UsuarioModel usuario2 = new UsuarioModel();
        usuario2.setId(2L);
        usuario2.setNombre("Juan");
        usuario2.setCorreo("juan@gmail.com");
        usuario2.setRol("ADMIN");

        when(usuarioRepository.findAll())
                .thenReturn(java.util.List.of(usuario1, usuario2));

        var resultado = usuarioService.obtenerTodos();

        assertEquals(2, resultado.size());
        }


    @Test
    void actualizarUsuarioDebeModificarDatos() {

        UsuarioModel usuario = new UsuarioModel();
        usuario.setId(1L);
        usuario.setNombre("Patricio");
        usuario.setCorreo("viejo@gmail.com");
        usuario.setPassword("1234");

        UsuarioRequestDTO dto = new UsuarioRequestDTO();
        dto.setNombre("Patricio Actualizado");
        dto.setCorreo("nuevo@gmail.com");
        dto.setPassword("5678");
        dto.setRol("CLIENTE");

        when(usuarioRepository.findById(1L))
                .thenReturn(Optional.of(usuario));

        when(usuarioRepository.save(any()))
                .thenReturn(usuario);

        UsuarioResponseDTO resultado =
                usuarioService.actualizarUsuario(1L, dto);

        assertEquals("Patricio Actualizado", resultado.getNombre());

        verify(usuarioRepository).save(any());
        }

        @Test
        void eliminarUsuarioDebeEliminarCorrectamente() {

        UsuarioModel usuario = new UsuarioModel();
        usuario.setId(1L);

        when(usuarioRepository.findById(1L))
                .thenReturn(Optional.of(usuario));

        usuarioService.eliminarUsuario(1L);

        verify(usuarioRepository).delete(usuario);
}
}





/* 
@ExtendWith(MockitoExtension.class)
class UsuarioServiceImplTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private JwtUtil jwtUtil;

    @InjectMocks
    private UsuarioServiceImplTest usuarioService;



    //test 1 : obtenerPorId()
    @Test
    void obtenerUsuarioPorId() {

        UsuarioModel usuario = new UsuarioModel();

        usuario.setId(1L);
        usuario.setNombre("Patricio");
        usuario.setCorreo("patricio@gmail.com");
        usuario.setRol("CLIENTE");

        when(usuarioRepository.findById(1L))
                .thenReturn(Optional.of(usuario));

        UsuarioResponseDTO resultado =
                UsuarioServiceImplTest.obtenerPorId(1L);

        assertEquals("Patricio", resultado.getNombre());
        assertEquals("CLIENTE", resultado.getRol());
    }

    //Test 2: crearUsuario()
    @Test
    void crearUsuarioDebeGuardarCorrectamente() {

        UsuarioRequestDTO dto =
                new UsuarioRequestDTO();

        dto.setNombre("Patricio");
        dto.setCorreo("pato@gmail.com");
        dto.setPassword("1234");

        UsuarioModel usuarioGuardado =
                new UsuarioModel();

        usuarioGuardado.setId(1L);
        usuarioGuardado.setNombre("Patricio");
        usuarioGuardado.setCorreo("pato@gmail.com");
        usuarioGuardado.setRol("CLIENTE");

        when(usuarioRepository.save(any()))
                .thenReturn(usuarioGuardado);

        UsuarioResponseDTO resultado =
                UsuarioServiceImplTest.crearUsuario(dto);

        assertEquals("Patricio",
                resultado.getNombre());

        verify(usuarioRepository)
                .save(any());
    }


    //test3 : login

    @Test
    void loginDebeRetornarToken() {

        LoginDTO dto =
                new LoginDTO();

        dto.setCorreo("pato@gmail.com");
        dto.setPassword("1234");

        UsuarioModel usuario =
                new UsuarioModel();

        usuario.setCorreo("pato@gmail.com");
        usuario.setPassword("1234");

        when(usuarioRepository
                .findByCorreo("pato@gmail.com"))
                .thenReturn(Optional.of(usuario));

        when(jwtUtil.generarToken("pato@gmail.com"))
                .thenReturn("TOKEN123");

        String token =
                usuarioService.login(dto);

        assertEquals("TOKEN123", token);
    }

}

*/