package com.clienteweb.app.springbootclienteapp.Service;

import com.clienteweb.app.springbootclienteapp.Entity.Usuario;
import com.clienteweb.app.springbootclienteapp.Repository.UsuarioRepositorio;
import com.clienteweb.app.springbootclienteapp.Token.ConfirmacionToken;
import com.clienteweb.app.springbootclienteapp.Token.ConfirmacionTokenServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class UsuarioService implements UserDetailsService {

    private final static String USUARIO_NO_ENCONTRADO = "Usuario %s no encontrado";

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private ConfirmacionTokenServicio confirmacionTokenServicio;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return usuarioRepositorio.findByEmail(email).orElseThrow(()-> new UsernameNotFoundException(String.format(USUARIO_NO_ENCONTRADO, email)));
    }

    public String registrarUsuario(Usuario usuario){
        boolean existe = usuarioRepositorio.findByEmail(usuario.getEmail()).isPresent();

        if (existe){
            throw new IllegalStateException("Email ya existe");
        }

        String encodePass = bCryptPasswordEncoder.encode(usuario.getPassword());
        usuario.setPassword(encodePass);

        usuarioRepositorio.save(usuario);

        String token = UUID.randomUUID().toString();
        ConfirmacionToken confirmacionToken = new ConfirmacionToken(token, LocalDateTime.now(), LocalDateTime.now().plusMinutes(15), usuario);

        confirmacionTokenServicio.guardarConfirmacionToken(confirmacionToken);

        return token;
    }
    public int habilitarUsuario(String email){
        return usuarioRepositorio.habilitarUsuario(email);
    }
}
