package br.itb.projeto.padaria3x.control;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.itb.projeto.padaria3x.model.entity.Usuario;
import br.itb.projeto.padaria3x.model.service.UsuarioService;

@Controller
@RequestMapping("/api/usuario")
public class UsuarioController {

	private UsuarioService usuarioService;

	public UsuarioController(UsuarioService usuarioService) {
		super();
		this.usuarioService = usuarioService;
	}

	@GetMapping("/todos")
	public String index(Model model) {
		List<Usuario> usuarios = usuarioService.findAll();
			
		model.addAttribute("usuarios", usuarios);
		
		return "usuarios";
	}
	
	private String serverMessage = null;
	
	@GetMapping("/login")
	public String getLogin(ModelMap model) {
		
		model.addAttribute("usuario", new Usuario());
		model.addAttribute("serverMessage", serverMessage);
		
		return "login";
	}

	@PostMapping("/acessar")
	public String acessar(
			@RequestParam("email") String email, 
			@RequestParam("senha") String senha, ModelMap model) {
		
		int acesso = usuarioService.acessar(email, senha);
		
		if (acesso == 1) {
			
			return "redirect:/api/usuario/todos";
			
		} else if (acesso == 2) {
			
			return "usuarios";
			
		}
		
		serverMessage = "Dados Incorretos!";
		model.addAttribute("serverMessage", serverMessage);
		
		return "redirect:/api/usuario/login";
		
	}
	
	
	
}
