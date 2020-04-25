package com.Petin.FantasyOfLegend.Rest;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

import org.hibernate.*;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.persistence.Query;

import com.Petin.FantasyOfLegend.Dao.UsuarioDao;
import com.Petin.FantasyOfLegend.Entity.Usuario;

@org.springframework.web.bind.annotation.RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET, RequestMethod.POST})
@RequestMapping("/usuarios")
public class UsuarioImpl {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Transactional
	@PostMapping
	public void insert(@RequestBody Usuario usuario){
		entityManager.createNativeQuery("INSERT INTO usuario (nombre,apellido,correo,alias,contrasena) VALUES (?,?,?,?,?)")
			.setParameter(1, usuario.getNombre())
			.setParameter(2, usuario.getApellido())
			.setParameter(3, usuario.getCorreo())
			.setParameter(4, usuario.getAlias())
			.setParameter(5, usuario.getContrasena())
			.executeUpdate();
		entityManager.close();
	}
}
