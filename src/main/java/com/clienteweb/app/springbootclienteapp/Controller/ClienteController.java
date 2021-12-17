package com.clienteweb.app.springbootclienteapp.Controller;


import com.clienteweb.app.springbootclienteapp.Entity.Ciudad;
import com.clienteweb.app.springbootclienteapp.Entity.Cliente;
import com.clienteweb.app.springbootclienteapp.Service.CiudadService;
import com.clienteweb.app.springbootclienteapp.Service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/views/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;
    @Autowired
    private CiudadService ciudadService;

    @GetMapping("/")
    public String listarClientes(Model model) {

        List<Cliente> listadoClientes = clienteService.mostrarClientes();

        model.addAttribute("titulo", "Lista de clientes");
        model.addAttribute("clientes", listadoClientes);
        return "/views/clientes/listar";
    }

    @GetMapping("/create")
    public String crearCliente(Model model) {

        Cliente cliente = new Cliente();
        List<Ciudad> listCiudades = ciudadService.listaCiudades();

        model.addAttribute("titulo", "Formulario: Nuevo Cliente");
        model.addAttribute("cliente", cliente);
        model.addAttribute("ciudades", listCiudades);

        return "/views/clientes/frmCrear";
    }

    @PostMapping("/save")
    public String guardar(@ModelAttribute Cliente cliente, Model model, RedirectAttributes redirectAttributes) {

        if (cliente.getId() == null) {
            try {
                clienteService.agregarCliente(cliente);

                List<Ciudad> listCiudades = ciudadService.listaCiudades();
                redirectAttributes.addFlashAttribute("mensaje", "Cliente " + cliente.getNombres() +" agregado");
                redirectAttributes.addFlashAttribute("titulo", "Formulario: Nuevo Cliente");

                redirectAttributes.addFlashAttribute("ciudades", listCiudades);
                return "redirect:/views/clientes/";


            } catch (Exception e) {
                Cliente cliente2 = cliente;
                List<Ciudad> listCiudades = ciudadService.listaCiudades();
                model.addAttribute("error", e.getMessage());
                model.addAttribute("titulo", "Formulario: Nuevo Cliente");
                model.addAttribute("cliente", cliente2);
                model.addAttribute("ciudades", listCiudades);
                return "/views/clientes/frmCrear";
            }
        } else {
            try {
                clienteService.editarCliente(cliente);

                redirectAttributes.addFlashAttribute("mensaje", "Cliente "+ cliente.getEmail() +" editado");
                return "redirect:/views/clientes/";
            } catch (Exception x) {
                redirectAttributes.addFlashAttribute("error", x);
                return "redirect:/views/clientes/frmCrear/";
            }
        }


    }

    @GetMapping("/edit/{id}")
    public String editarCliente(@PathVariable("id") Long idCliente, Model model) {

        Cliente cliente = clienteService.buscarCliente(idCliente);
        List<Ciudad> listCiudades = ciudadService.listaCiudades();

        model.addAttribute("titulo", "Formulario: Editar Cliente");
        model.addAttribute("cliente", cliente);
        model.addAttribute("ciudades", listCiudades);

        return "/views/clientes/frmCrear";
    }

    @GetMapping("/delete/{id}")
    public String eliminarCliente(@PathVariable("id") Long idCliente, RedirectAttributes redirectAttributes) {

        clienteService.eliminarCliente(idCliente);
        redirectAttributes.addFlashAttribute("mensaje", "Cliente eliminado");

        return "redirect:/views/clientes/";
    }

}
