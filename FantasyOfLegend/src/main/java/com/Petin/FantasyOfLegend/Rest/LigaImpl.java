package com.Petin.FantasyOfLegend.Rest;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.Petin.FantasyOfLegend.Dao.LigaDao;
import com.Petin.FantasyOfLegend.Dao.UsuarioDao;
import com.Petin.FantasyOfLegend.Entity.Liga;
import com.Petin.FantasyOfLegend.Entity.Usuario;

@org.springframework.web.bind.annotation.RestController
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST })
public class LigaImpl {

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	private UsuarioDao usu;

	@Autowired
	private LigaDao li;

	@Transactional(propagation = Propagation.NESTED)
	@PostMapping
	@RequestMapping("/liga/union/{codigo}/{id}")
	public String[] unirse(@PathVariable String codigo, @PathVariable Integer id) {
		String[] resultadoString = new String[1];
		Query query = entityManager.createNativeQuery("Select nombre from liga where codigo= ?").setParameter(1,
				codigo);
		List<String> resultado = query.getResultList();
		if (resultado.size() != 0) {
			String nombre = resultado.get(0);
			query = entityManager.createNativeQuery("Select id from liga where nombre= ?").setParameter(1, nombre);
			List<Integer> resultList = query.getResultList();
			int idLiga = resultList.get(0);
			query = entityManager.createNativeQuery("Select id from usuario where fk_liga = ?").setParameter(1, idLiga);
			resultList = query.getResultList();
			if(resultList.size()<6) {
				entityManager.createNativeQuery("Update Usuario set fk_liga=? where id= ?").setParameter(1, idLiga)
						.setParameter(2, id).executeUpdate();
				entityManager.close();
				resultadoString[0] = "success";
				return resultadoString;
			}else {
				resultadoString[0] = "No se pueden unir más personas a esta liga";
				return resultadoString;
			}
			
		} else {
			entityManager.close();
			resultadoString[0] = "No existe una liga con ese codigo";
			return resultadoString;
		}
	}

	@Transactional(propagation = Propagation.NESTED)
	@PostMapping
	@RequestMapping("/liga/comprobar/{id}")
	public Optional<Liga> comprobar(@PathVariable Integer id) {
		Query query = entityManager.createNativeQuery("Select fk_liga from usuario where id= ?").setParameter(1, id);
		int resultado = (int) query.getResultList().get(0);
		if (resultado != 1) {
			return li.findById(resultado);
		} else {
			return null;
		}
	}

	@PostMapping
	@RequestMapping("/liga/usuarios/{id}")
	public List<Optional> usuariosLiga(@PathVariable Integer id) {
		List<Optional> usuarios = new ArrayList<Optional>();
		Query query = entityManager.createNativeQuery("Select fk_liga from usuario where id= ?").setParameter(1, id);
		int resultado = (int) query.getResultList().get(0);
		query = entityManager.createNativeQuery("Select id from usuario where fk_liga = ?").setParameter(1, resultado);
		List<Integer> resultados = query.getResultList();
		System.out.println(resultados);
		for(int i=0; i < resultados.size(); i++) {
			System.out.println(resultados.get(i));
			System.out.println(usu.findById(resultados.get(i)).toString());
			usuarios.add(usu.findById(resultados.get(i)));
		}
		return usuarios;
	}
	
	@Transactional(propagation = Propagation.NESTED)
	@PostMapping
	@RequestMapping("/liga/creacion/{id}")
	public String[] crearLiga(@RequestBody Liga liga, @PathVariable Integer id) {
		String[] resultado = new String[2];
		Query query = entityManager.createNativeQuery("Select id from liga where nombre= ?").setParameter(1,
				liga.getNombre());
		List<Integer> resultList = query.getResultList();
		System.out.println(resultList);
		if (resultList.size() == 0) {
			String codigoR = codigoRandom();
			entityManager.createNativeQuery("INSERT INTO Liga (nombre, descripcion, codigo) VALUES (?,?,?)")
					.setParameter(1, liga.getNombre()).setParameter(2, liga.getDescripcion()).setParameter(3, codigoR)
					.executeUpdate();
			query = entityManager.createNativeQuery("Select id from liga where nombre= ?").setParameter(1,
					liga.getNombre());
			resultList = query.getResultList();
			nuevoMercado(resultList.get(0));
			query = entityManager.createNativeQuery("Select id from liga where nombre= ?").setParameter(1,
					liga.getNombre());
			resultList = query.getResultList();
			asignarNuevaLiga(resultList.get(0), id);
			resultado[0] = "success";
			resultado[1] = codigoR;
			return resultado;
		} else {
			entityManager.close();
			resultado[0] = "Ya existe una liga con ese nombre.";
			return resultado;
		}
	}

	public String codigoRandom() {
		return RandomStringUtils.randomAlphanumeric(10);
	}

	public void nuevoMercado(int id) {
		entityManager.createNativeQuery("INSERT INTO Mercado (fk_liga) VALUES (?)").setParameter(1, id).executeUpdate();
	}

	public void asignarNuevaLiga(int id_liga, int id_usuario) {
		entityManager.createNativeQuery("Update Usuario set fk_liga=? where id= ?").setParameter(1, id_liga)
				.setParameter(2, id_usuario).executeUpdate();
		entityManager.close();
	}
}
