package br.com.lucas.apirest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.lucas.apirest.models.Usuario;
import br.com.lucas.apirest.repositories.UsuarioRepository;


@RestController
@RequestMapping(value = "/usuario")
public class UsuarioController {
	
	//Injeção de dependência
	@Autowired
	private UsuarioRepository usuarioRepository;
	
    
	@PostMapping(value = "/")
    public ResponseEntity<Usuario> salvar(@RequestBody Usuario usuario){
		
		if (usuario.getId() == null) {
    	Usuario usuarioSalvo = this.usuarioRepository.save(usuario);
    	
    	return new ResponseEntity<Usuario>(usuarioSalvo, HttpStatus.CREATED);
    	
		}
		
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
	
	@DeleteMapping(value = "/{idUsuario}")
	public ResponseEntity<String> excluir(@PathVariable Long idUsuario) {
		
		if (idUsuario != null) {
		
			this.usuarioRepository.deleteById(idUsuario);
			
			return new ResponseEntity<String>("Usuário Excluído com Sucesso", HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
    
    
    
	
    @GetMapping(value = "/editar/{idUsuario}")
    public ResponseEntity<Usuario> pesquisarUsuario(@PathVariable Long idUsuario){
    	
    	Usuario usuario = this.usuarioRepository.findById(idUsuario).get();
    	
    	return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
    }
    
    @PutMapping(value = "/")
    public ResponseEntity<?> atualizar(@RequestBody Usuario usuario){
    	
    	if (usuario.getId() != null) {
    		
    		usuarioRepository.save(usuario);
    		
    		return new ResponseEntity<String>("Usuário atualizado com sucesso!", HttpStatus.OK);	
    	}
    	
    	return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    
    }
    
    @GetMapping(value = "/{nome}")
    public ResponseEntity<List<Usuario>> buscarPorNome(@PathVariable String nome){
    	
    	
    	if (nome.equalsIgnoreCase("todos")) {
    		List<Usuario> lista = this.usuarioRepository.findAll();
    		return new ResponseEntity<List<Usuario>>(lista, HttpStatus.OK);
    	}
    	
    	List<Usuario> listaUsuario = this.usuarioRepository.buscarPorNome(nome.trim().toUpperCase());
    	
    	return new ResponseEntity<List<Usuario>>(listaUsuario, HttpStatus.OK);
    }
    
}
