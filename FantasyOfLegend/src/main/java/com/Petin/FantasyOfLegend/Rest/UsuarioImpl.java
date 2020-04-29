package com.Petin.FantasyOfLegend.Rest;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.persistence.Query;

import com.Petin.FantasyOfLegend.Dao.UsuarioDao;
import com.Petin.FantasyOfLegend.Entity.Usuario;

@org.springframework.web.bind.annotation.RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET, RequestMethod.POST})
public class UsuarioImpl {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private UsuarioDao usu;
	
	@Transactional
	@PostMapping
	@RequestMapping("/usuarios/insert")
	public boolean insert(@RequestBody Usuario usuario){
		boolean existe = false;
		Query query = entityManager.createNativeQuery("Select id from usuario where alias= ?")
				.setParameter(1, usuario.getAlias());
		List<Integer> resultList = query.getResultList();
		if(resultList.size()>0) {
			return false;
		} else {
			query = entityManager.createNativeQuery("Select id from usuario where correo= ?")
					.setParameter(1, usuario.getCorreo());
			resultList = query.getResultList();
			if(resultList.size()>0) {
				return false;
			} else {
				entityManager.createNativeQuery("INSERT INTO usuario (nombre,apellido,correo,alias,contrasena) VALUES (?,?,?,?,hex(AES_ENCRYPT(?,'PATATON')))")
				.setParameter(1, usuario.getNombre())
				.setParameter(2, usuario.getApellido())
				.setParameter(3, usuario.getCorreo())
				.setParameter(4, usuario.getAlias())
				.setParameter(5, usuario.getContrasena())
				.executeUpdate();
			entityManager.close();
			}
			return true;
		}
	}
	
	@PostMapping
	@RequestMapping("/usuarios/login")
	public Optional<Usuario> iniciarSesion(@RequestBody Usuario usuario) {
		Query query = entityManager.createNativeQuery("Select id from usuario where alias= ? and contrasena = hex(AES_ENCRYPT(?,'PATATON'))")
				.setParameter(1, usuario.getAlias())
				.setParameter(2, usuario.getContrasena());
		
		try {
			int result = (int) query.getSingleResult();
			entityManager.close();		
			return usu.findById(result);
		} catch (Exception e) {
			return null;
		}
	}
	
	@Transactional(propagation = Propagation.NESTED)
	@PostMapping
	@RequestMapping("/usuarios/edit")
	public Optional<Usuario> edit(@RequestBody Usuario usuario) {
		System.out.println(usuario.toString());
		Query query = entityManager.createNativeQuery("Select id from usuario where alias= ?")
				.setParameter(1, usuario.getAlias());
		List<Integer> resultList = query.getResultList();
		if(resultList.size()>0) {
			int result =(int) query.getSingleResult();
			try {
				entityManager.createNativeQuery("Update Usuario set nombre=?,apellido=?,correo=?,contrasena=hex(AES_ENCRYPT(?,'PATATON')) where id= ?")
						.setParameter(1, usuario.getNombre())
						.setParameter(2, usuario.getApellido())
						.setParameter(3, usuario.getCorreo())
						.setParameter(4, usuario.getContrasena())
						.setParameter(5, result)
						.executeUpdate();
				entityManager.close();
				return usu.findById(result);
			} catch (Exception e) {
				return null;
			}
		}else {
			return null;
		}
	}
}
