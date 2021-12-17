package com.clienteweb.app.springbootclienteapp.Repository;

import com.clienteweb.app.springbootclienteapp.Entity.Ciudad;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CiudadRepository extends JpaRepository<Ciudad, Long> {

}
