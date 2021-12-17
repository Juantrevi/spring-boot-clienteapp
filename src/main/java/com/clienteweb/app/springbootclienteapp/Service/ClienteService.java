package com.clienteweb.app.springbootclienteapp.Service;

import com.clienteweb.app.springbootclienteapp.Entity.Cliente;
import com.clienteweb.app.springbootclienteapp.Repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;


    @Transactional
    public void agregarCliente(Cliente cliente){

        Optional<Cliente> respuesta = Optional.ofNullable(clienteRepository.findClienteByEmail(cliente.getEmail()));

        if (respuesta.isPresent()){
            throw new IllegalStateException("Cliente ya existe por email.");
        } else {
            Cliente clienten = new Cliente(cliente.getNombres(), cliente.getApellidos(), cliente.getTelefono(), cliente.getEmail(), cliente.getCiudad());
            clienteRepository.save(clienten);
        }

    }

    public List<Cliente> mostrarClientes(){
        return clienteRepository.findAll();
    }

    public Cliente buscarCliente(Long id){
        Optional<Cliente> respuesta = clienteRepository.findById(id);

        if (respuesta.isEmpty()){
            throw new IllegalStateException("Cliente no existe");
        }else{
            return respuesta.get();
        }
    }

    @Transactional
    public void editarCliente(Cliente cliente){
        Optional<Cliente> respuesta = clienteRepository.findById(cliente.getId());
        if (respuesta.isEmpty()){
            throw new IllegalStateException("Cliente a editar no existe");
        }
        else {
            Cliente cliente1 = respuesta.get();
            cliente1.setNombres(cliente.getNombres());
            cliente1.setApellidos(cliente.getApellidos());
            cliente1.setTelefono(cliente.getTelefono());
            cliente1.setEmail(cliente.getEmail());
            cliente1.setCiudad(cliente.getCiudad());

            clienteRepository.save(cliente1);
        }
    }

    public void eliminarCliente(Long id){
        Optional<Cliente> respuesta = clienteRepository.findById(id);

        if (respuesta.isEmpty()){
            throw new IllegalStateException("Cliente a eliminar inexistente");
        }else {
            clienteRepository.deleteById(id);
        }
    }





}
