package com.clienteweb.app.springbootclienteapp.Service;

import com.clienteweb.app.springbootclienteapp.Entity.Ciudad;
import com.clienteweb.app.springbootclienteapp.Repository.CiudadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CiudadService {

    @Autowired
    private CiudadRepository ciudadRepository;


    public List<Ciudad> listaCiudades(){
        return ciudadRepository.findAll();
    }
}
