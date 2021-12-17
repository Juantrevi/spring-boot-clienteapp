package com.clienteweb.app.springbootclienteapp.Token;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service

public class ConfirmacionTokenServicio {

    @Autowired
    private ConfirmacionTokenRepositorio confirmacionTokenRepositorio;

    public void guardarConfirmacionToken(ConfirmacionToken token){
        confirmacionTokenRepositorio.save(token);
    }
    public Optional<ConfirmacionToken> getToken(String token){
        return confirmacionTokenRepositorio.findByToken(token);
    }

    public int setConfirmadoEn(String token){
        return confirmacionTokenRepositorio.editarConfirmadoEn(token, LocalDateTime.now());
    }
}
