package com.Petin.FantasyOfLegend.Rest;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Query;

import com.Petin.FantasyOfLegend.Dao.UsuarioDao;
import com.Petin.FantasyOfLegend.Entity.Mensaje;
import com.Petin.FantasyOfLegend.Entity.Usuario;

import net.bytebuddy.dynamic.scaffold.MethodRegistry.Handler.ForImplementation;

@org.springframework.web.bind.annotation.RestController
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST })
public class UsuarioImpl {

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	private UsuarioDao usu;

	@Autowired
	private JavaMailSender mailSender;

	@Transactional
	@PostMapping
	@RequestMapping("/usuarios/insert")
	public String[] insert(@RequestBody Usuario usuario) {
		System.out.println(usuario.toString());
		String[] mensaje = new String[1];
		boolean existe = false;
		Query query = entityManager.createNativeQuery("Select id from usuario where alias= ?").setParameter(1,
				usuario.getAlias());
		List<Integer> resultList = query.getResultList();
		if (resultList.size() > 0) {
			mensaje[0]="alias";
			return mensaje;
		} else {
			query = entityManager.createNativeQuery("Select id from usuario where correo= ?").setParameter(1,
					usuario.getCorreo());
			resultList = query.getResultList();
			if (resultList.size() > 0) {
				mensaje[0]="correo";
				return mensaje;
			} else {
				System.out.println(usuario);
				entityManager.createNativeQuery(
						"INSERT INTO usuario (nombre,apellido,correo,alias,contrasena, logo) VALUES (?,?,?,?,hex(AES_ENCRYPT(?,'PATATON')),?)")
						.setParameter(1, usuario.getNombre()).setParameter(2, usuario.getApellido())
						.setParameter(3, usuario.getCorreo()).setParameter(4, usuario.getAlias())
						.setParameter(5, usuario.getContrasena()).setParameter(6, usuario.getLogo()).executeUpdate();
				entityManager.close();
			}
			mensaje[0]="bien";
			return mensaje;
		}
	}

	@PostMapping
	@RequestMapping("/usuarios/login")
	public Optional<Usuario> iniciarSesion(@RequestBody Usuario usuario) {
		Query query = entityManager
				.createNativeQuery(
						"Select id from usuario where alias= ? and contrasena = hex(AES_ENCRYPT(?,'PATATON'))")
				.setParameter(1, usuario.getAlias()).setParameter(2, usuario.getContrasena());

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
		Query query = entityManager.createNativeQuery("Select id from usuario where alias= ?").setParameter(1,
				usuario.getAlias());
		List<Integer> resultList = query.getResultList();
		if (resultList.size() > 0) {
			int result = (int) resultList.get(0);
			try {
				entityManager.createNativeQuery("Update Usuario set nombre=?,apellido=?,correo=?,contrasena=hex(AES_ENCRYPT(?,'PATATON')), logo=? where id= ?")
						.setParameter(1, usuario.getNombre()).setParameter(2, usuario.getApellido())
						.setParameter(3, usuario.getCorreo()).setParameter(4, usuario.getContrasena())
						.setParameter(5, usuario.getLogo()).setParameter(6, result)
						.executeUpdate();
				entityManager.close();
				return null;
			} catch (Exception e) {
				System.out.println(e);
				return null;
			}
		} else {
			return null;
		}
	}

	@Transactional(propagation = Propagation.NESTED)
	@PostMapping
	@RequestMapping("/usuarios/passwordOlvidada")
	public String[] contraseñaOlvidada(@RequestBody String alias) {
		String[] resultado = new String[1];
		Query query = entityManager.createNativeQuery("Select correo from usuario where alias= ?").setParameter(1,
				alias);
		List<String> resultList = query.getResultList();
		if (resultList.size() > 0) {
			String password = passwordAleatoria();
			String correo = resultList.get(0);
			entityManager
					.createNativeQuery("Update Usuario set contrasena=hex(AES_ENCRYPT(?,'PATATON')) where alias= ?")
					.setParameter(1, password).setParameter(2, alias).executeUpdate();
			entityManager.close();
			sendEmail(correo, password);
			resultado[0] = "success";
			return resultado;
		} else {
			resultado[0] = "error";
			return resultado;
		}
	}

	public void sendEmail(String to, String password) {

		SimpleMailMessage email = new SimpleMailMessage();

		email.setTo(to);
		email.setSubject("Recordar contraseña olvidada");
		email.setText("Hola"
				+ "\nHemos recibido que has olvidado la contraseña. Por lo que te entregamos el siguiente\ncodigo"
				+ "con el que podrás iniciar sesión. Recuerda que debes cambiar la contraseña\nen el apartado 'PERFIL'."
				+ "\n\nCODIGO PARA INICIAR SESIÓN:\n" + "						" + password + "\n"
				+ "Un saludo del equipo de Fantasy Of Legends. ¡A GANAR!");

		mailSender.send(email);
	}

	public String passwordAleatoria() {
		return RandomStringUtils.randomAlphanumeric(10);
	}
	
	
	
	@Transactional(propagation = Propagation.NESTED)
	@PostMapping
	@RequestMapping("/usuarios/contacto")
	public void sendEmail(@RequestBody Mensaje mensaje) {

		SimpleMailMessage email = new SimpleMailMessage();

		email.setTo("petsingame@gmail.com");
		email.setSubject(mensaje.nombre + " "+ mensaje.apellidos +" ha enviado una duda.");
		email.setText(mensaje.texto + "\n\n\nCorreo: "+mensaje.correo);

		mailSender.send(email);
	}

}
