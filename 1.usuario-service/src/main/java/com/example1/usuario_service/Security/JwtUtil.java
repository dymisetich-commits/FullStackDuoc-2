package com.example1.usuario_service.Security;

import java.security.Key;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;

@Component
public class JwtUtil {

     private final String SECRET =
            "mi_clave_super_secreta_microservicios";

    private final Key key = new SecretKeySpec(
            SECRET.getBytes(),
            io.jsonwebtoken.SignatureAlgorithm.HS256.getJcaName());

    // Generar token
    public String generarToken(String correo) {

        return Jwts.builder()
                .setSubject(correo)
                .setIssuedAt(new Date())
                .setExpiration(
                        new Date(System.currentTimeMillis()
                                + 1000 * 60 * 60))
                .signWith(key)
                .compact();
    }

    // Obtener correo desde token
    public String extraerCorreo(String token) {

        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    // Validar token
    public boolean validarToken(String token) {

        try {

            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);

            return true;

        } catch (Exception e) {

            return false;
        }
    }

}

