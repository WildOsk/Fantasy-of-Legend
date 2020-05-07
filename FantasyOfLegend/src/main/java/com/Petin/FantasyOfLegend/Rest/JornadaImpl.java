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

import com.Petin.FantasyOfLegend.Dao.JornadaDao;
import com.Petin.FantasyOfLegend.Dao.PartidoDao;
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

	@GetMapping
	@RequestMapping("/jornada")
	public List<Jornada> listarJornada(){
		return jor.findAll();
	}
	
	@GetMapping
	@RequestMapping("/jornada/{id}")
	public List<Optional> partidosJornada(@PathVariable Integer id){
		System.out.println("HOLA");
		List<Optional> partidos = new ArrayList<Optional>();
		Query query = entityManager.createNativeQuery("Select id from partido where fk_jornada= ?")
				.setParameter(1,id);
		List<Integer> resultado = query.getResultList();
		for(int i=0; i<resultado.size(); i++) {
			partidos.add(par.findById(resultado.get(i)));
		}
		return partidos;
	}
	
}
