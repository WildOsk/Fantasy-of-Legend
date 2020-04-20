package com.Petin.FantasyOfLegend.Rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.Petin.FantasyOfLegend.Dao.IEquipoDao;
import com.Petin.FantasyOfLegend.Dao.IJugadoresDao;
import com.Petin.FantasyOfLegend.Entity.Equipo;
import com.Petin.FantasyOfLegend.Entity.Jugador;

@org.springframework.web.bind.annotation.RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET})
public class RestController {
	
	@Autowired
	private IEquipoDao equ;
	@Autowired
	private IJugadoresDao jug;
	
	@GetMapping
	@RequestMapping("/equipos")
	public List<Equipo> listarE(){
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