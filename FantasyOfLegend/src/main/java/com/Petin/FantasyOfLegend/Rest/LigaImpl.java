package com.Petin.FantasyOfLegend.Rest;

import java.util.List;

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

	@Transactional(propagation = Propagation.NESTED)
	@PostMapping
	@RequestMapping("/liga/union/{codigo}/{id}")
	public String unirteLiga(@PathVariable String codigo, @PathVariable Integer id) {
		Query query = entityManager.createNativeQuery("Select fk_liga from usuario where id= ?").setParameter(1, id);
		List<Integer> resultList = query.getResultList();
		if (resultList.get(0) == 1) {
			query = entityManager.createNativeQuery("Select nombre from liga where codigo= ?")
					.setParameter(1,codigo);
			List<String> resultado = query.getResultList();
			if (resultado.size() != 0) {
				String nombre = resultado.get(0);
				query = entityManager.createNativeQuery("Select id from liga where nombre= ?").setParameter(1, nombre);
				resultList = query.getResultList();
				entityManager.createNativeQuery("Update Usuario set fk_liga=? where id= ?")
						.setParameter(1, resultList.get(0)).setParameter(2, id).executeUpdate();
				entityManager.close();
				return "Unido a la liga: " + nombre;
			} else {
				entityManager.close();
				return "No existe una liga con ese codigo";
			}
		} else {
			entityManager.close();
			return "Usuario ya tiene liga.";
		}
	}

	@Transactional(propagation = Propagation.NESTED)
	@PostMapping
	@RequestMapping("/liga/creacion/{id}")
	public String crearLiga(@RequestBody Liga liga, @PathVariable Integer id) {
		Query query = entityManager.createNativeQuery("Select fk_liga from usuario where id= ?").setParameter(1, id);
		List<Integer> resultList = query.getResultList();
		if (resultList.get(0) == 1) {
			query = entityManager.createNativeQuery("Select id from liga where nombre= ?").setParameter(1,
					liga.getNombre());
			resultList = query.getResultList();
			System.out.println(resultList);
			if (resultList.size() == 0) {
				nuevaLiga(liga);
				query = entityManager.createNativeQuery("Select id from liga where nombre= ?").setParameter(1,
						liga.getNombre());
				resultList = query.getResultList();
				nuevoMercado(resultList.get(0));
				query = entityManager.createNativeQuery("Select id from liga where nombre= ?").setParameter(1,
						liga.getNombre());
				resultList = query.getResultList();
				asignarNuevaLiga(resultList.get(0), id);
				return "Liga creada";
			} else {
				entityManager.close();
				return "Ya existe una liga con ese nombre";
			}
		} else {
			entityManager.close();
			return "Usuario ya tiene liga.";
		}
	}

	public String codigoRandom() {
		return RandomStringUtils.randomAlphanumeric(10);
	}

	public void nuevoMercado(int id) {
		entityManager.createNativeQuery("INSERT INTO Mercado (fk_liga) VALUES (?)")
			.setParameter(1, id)
			.executeUpdate();
	}

	public void nuevaLiga(Liga liga) {
		entityManager.createNativeQuery("INSERT INTO Liga (nombre, descripcion, codigo) VALUES (?,?,?)")
			.setParameter(1, liga.getNombre())
			.setParameter(2, liga.getDescripcion())
			.setParameter(3, codigoRandom())
			.executeUpdate();
	}

	public void asignarNuevaLiga(int id_liga, int id_usuario) {
		entityManager.createNativeQuery("Update Usuario set fk_liga=? where id= ?")
			.setParameter(1, id_liga)
			.setParameter(2, id_usuario)
			.executeUpdate();
		entityManager.close();
	}
}
