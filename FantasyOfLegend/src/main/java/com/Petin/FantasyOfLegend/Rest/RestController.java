package com.Petin.FantasyOfLegend.Rest;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.persistence.Query;

import com.Petin.FantasyOfLegend.Dao.EquipoDao;
import com.Petin.FantasyOfLegend.Dao.JugadoresDao;
import com.Petin.FantasyOfLegend.Entity.Equipo;
import com.Petin.FantasyOfLegend.Entity.Jugador;

@org.springframework.web.bind.annotation.RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET})
public class RestController {
	
	@Autowired
	private EntityManager em;
	
	@Autowired
	private EquipoDao equ;
	@Autowired
	private JugadoresDao jug;
	
	@GetMapping
	@RequestMapping("/equipos")
	public List<Equipo> listarE(){
		return equ.findAll();
	}
	
	@Transactional
	@GetMapping
	@RequestMapping("/equipos/insert")
	public List<Equipo> anadirE(){
		Query query = em.createNativeQuery("insert into equipo(nombre,logo) values(?,?)");
		query.setParameter(1, "a");
		query.setParameter(2, "a");
		query.executeUpdate();
		return equ.findAll();
	}
	
	@GetMapping
	@RequestMapping("/jugadores")
	public List<Jugador> listarJ(){
		return jug.findAll();
	}
	
	@GetMapping
	@RequestMapping("/{id}")
	public Optional<Equipo> mostrar(@PathVariable Integer id){
		return equ.findById(id);
	}		
}