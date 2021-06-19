package br.com.lucas.apirest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.lucas.apirest.model.Usuario;
import br.com.lucas.apirest.repository.UsuarioRepository;


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
    
    @PutMapping(value = "atualizar")
    @ResponseBody
    public ResponseEntity<?> atualizar(@RequestBody Usuario usuario){
    	
    	if (usuario.getId() == null) {
    		return new ResponseEntity<String>("ID não foi infomardo", HttpStatus.OK);
    	}
    	
    	Usuario user = usuarioRepository.saveAndFlush(usuario);
    	return new ResponseEntity<Usuario>(user, HttpStatus.OK);
    
    }
    
    @GetMapping(value = "pesquisarPorNome")
    @ResponseBody
    public ResponseEntity<List<Usuario>> buscarPorNome(@RequestParam(name = "nome") String nome){
    	
    	List<Usuario> listaUsuario = usuarioRepository.buscarPorNome(nome.trim().toUpperCase());
    	
    	return new ResponseEntity<List<Usuario>>(listaUsuario, HttpStatus.OK);
    }
    
}
