package com.clienteweb.app.springbootclienteapp.Controller;


import com.clienteweb.app.springbootclienteapp.Registro.RegistroPedido;
import com.clienteweb.app.springbootclienteapp.Registro.RegistroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/usuario")
public class MailController {

    @Autowired
    private RegistroService registroService;

    @GetMapping( "/crearUsuario")
    public String crearUsuario(Model model){

        RegistroPedido pedido = new RegistroPedido();
        model.addAttribute("pedido", pedido);

        return "usuarios/usuario";
    }

//

    @PostMapping( "/confirmacion")
    public String confirmacion(@ModelAttribute RegistroPedido pedido){

        registroService.registro(pedido);

        return "/views/clientes/listar";
    }

    @GetMapping("/confirmartoken/{token}")
    public String confirmartoken(@PathVariable("token") String token){
        registroService.confirmarToken(token);
        return "views/clientes/listar";
    }
}
