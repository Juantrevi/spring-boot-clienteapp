package com.clienteweb.app.springbootclienteapp.Repository;

import com.clienteweb.app.springbootclienteapp.Entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository <Cliente, Long> {

    Cliente findClienteByNombresAndApellidos(String nombres, String apellidos);

    Cliente findClienteByEmail(String email);
}
