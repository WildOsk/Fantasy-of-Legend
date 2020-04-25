package com.Petin.FantasyOfLegend.Rest;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.Petin.FantasyOfLegend.Dao.EquipoDao;
import com.Petin.FantasyOfLegend.Dao.UsuarioDao;
import com.Petin.FantasyOfLegend.Entity.Usuario;

@org.springframework.web.bind.annotation.RestController
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST })
@RequestMapping("/sesion")
public class Sesion {

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	private UsuarioDao usu;

	@PostMapping
	public Optional<Usuario> iniciarSesion(@RequestBody Usuario usuario) {
		Query query = entityManager.createNativeQuery("Select id from usuario where alias= ? and contrasena = ?")
				.setParameter(1, usuario.getAlias()).setParameter(2, usuario.getContrasena());
		int result = -1;
		try {
			result = (int) query.getSingleResult();
			entityManager.close();		
			return usu.findById(result);
		} catch (Exception e) {
			return null;
		}
		
	}

}
