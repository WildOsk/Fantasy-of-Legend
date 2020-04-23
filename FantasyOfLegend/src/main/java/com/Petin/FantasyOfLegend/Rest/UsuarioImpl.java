package com.Petin.FantasyOfLegend.Rest;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Petin.FantasyOfLegend.Dao.UsuarioDao;
import com.Petin.FantasyOfLegend.Entity.Usuario;

@RestController
@RequestMapping("/usuarios")
public class UsuarioImpl {
	
	@Autowired
	private EntityManager em;
	
	@Autowired
	private UsuarioDao usuarios;

	@GetMapping
	@RequestMapping("/usuarios/all")
	public List<Usuario> getAll(){
		return usuarios.findAll();
	}
	
	@Transactional
	@PostMapping
	private void insert(@RequestBody Usuario usuario){
			System.out.println(usuario);
			Query query = em.createNativeQuery("insert into usuario(nombre,apellido,correo,alias,contrasena) values(?,?,?,?,?)");
			query.setParameter(1, usuario.getNombre());
			query.setParameter(2, usuario.getApellido());
			query.setParameter(3, usuario.getCorreo());
			query.setParameter(4, usuario.getAlias());
			query.setParameter(5, usuario.getContrasena());
			query.executeUpdate();
	}
}
