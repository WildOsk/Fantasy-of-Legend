package com.Petin.FantasyOfLegend.Rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.Petin.FantasyOfLegend.Dao.EquipoDao;
import com.Petin.FantasyOfLegend.Dao.JornadaDao;
import com.Petin.FantasyOfLegend.Dao.PartidoDao;
import com.Petin.FantasyOfLegend.Entity.Equipo;
import com.Petin.FantasyOfLegend.Entity.Jornada;
import com.Petin.FantasyOfLegend.Entity.Partido;

@org.springframework.web.bind.annotation.RestController
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST })
public class JornadaImpl {

	@PersistenceContext
	private EntityManager entityManager;
	@Autowired
	private JornadaDao jor;
	@Autowired
	private PartidoDao par;
	@Autowired
	private EquipoDao equ;

	@GetMapping
	@RequestMapping("/jornada")
	public List<Jornada> listarJornada() {
		return jor.findAll();
	}

	@GetMapping
	@RequestMapping("/jornada/{id}")
	public List<Optional> partidosJornada(@PathVariable Integer id) {
		List<Optional> partidos = new ArrayList<>();
		List<Integer> idPartidos = (List<Integer>) entityManager
				.createNativeQuery("Select id from partido where fk_jornada= ?").setParameter(1, id).getResultList();
		for (int i = 0; i < idPartidos.size(); i++) {
			partidos.add(par.findById(idPartidos.get(i)));
		}
		return partidos;
	}

	@GetMapping
	@RequestMapping("/jornada/partido/{id}")
	public List<Equipo> equipoPartido(@PathVariable Integer id) {
		List<Equipo> equipos = new ArrayList<Equipo>();
		int local = (int) entityManager.createNativeQuery("Select e_local from partido where id= ?").setParameter(1, id).getSingleResult();
		String nombre = (String) entityManager.createNativeQuery("Select nombre from equipo where id= ?").setParameter(1, local).getSingleResult();
		String logo = (String) entityManager.createNativeQuery("Select logo from equipo where id= ?").setParameter(1, local).getSingleResult();
		Equipo e_local = new Equipo(local, nombre, logo);
		local = (int) entityManager.createNativeQuery("Select e_visitante from partido where id= ?").setParameter(1, id).getSingleResult();
		nombre = (String) entityManager.createNativeQuery("Select nombre from equipo where id= ?").setParameter(1, local).getSingleResult();
		logo = (String) entityManager.createNativeQuery("Select logo from equipo where id= ?").setParameter(1, local).getSingleResult();
		Equipo e_visitante = new Equipo(local, nombre, logo);
		equipos.add(e_local);
		equipos.add(e_visitante);
		return equipos;
	}

}
