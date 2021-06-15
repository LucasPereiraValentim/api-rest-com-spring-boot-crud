package br.com.lucas.apirest.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.lucas.apirest.model.Usuario;
import br.com.lucas.apirest.repository.UsuarioRepository;

/**
 *
 * A sample greetings controller to return greeting text
 */
@RestController
public class UsuarioController {
	
	//Injeção de dependência
	@Autowired
	private UsuarioRepository usuarioRepository;
	
    
	@PostMapping(value = "salvar")
	@ResponseBody
    public ResponseEntity<Usuario> salvar(@RequestBody Usuario usuario){
    	Usuario user = usuarioRepository.save(usuario);
    	
    	return new ResponseEntity<Usuario>(user, HttpStatus.CREATED);
    }
	
	@DeleteMapping(value = "excluir")
	@ResponseBody
	public ResponseEntity<String> excluir(@RequestParam(name = "idUsuario") Long idUsuario) {
		
		usuarioRepository.deleteById(idUsuario);
		
		return new ResponseEntity<String>("Usuário Excluído com Sucesso", HttpStatus.OK);
	}
    
    
    
    @GetMapping(value = "listatodos")
    @ResponseBody //Retorna os dados ao corpo da resposta
    public ResponseEntity<List<Usuario>> getListaUsuario(){
    	
    	List<Usuario> lista = usuarioRepository.findAll(); // Retorna todos os registros da tabela Usuario
    	
    	return new ResponseEntity<List<Usuario>>(lista, HttpStatus.OK); //retorna um JSON
    }
    
    @GetMapping(value = "buscarusuario")
    @ResponseBody
    public ResponseEntity<Usuario> pesquisarUsuario(@RequestParam(name = "idUsuario") Long idUsuario){
    	
    	Usuario usuario = usuarioRepository.findById(idUsuario).get();
    	
    	return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
    }
}
