package br.com.lucas.apirest.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.lucas.apirest.models.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	
	@Transactional(readOnly = true)
	@Query(value = "select u from Usuario u where upper(trim(u.nome)) like %?1%")
	public List<Usuario> buscarPorNome(String nome); 
	
	
	
}
