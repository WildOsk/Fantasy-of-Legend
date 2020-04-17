package com.Petin.FantasyOfLegend.Entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name="Jugador")
public class Jugador implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id")
	private int id;
	
	@Column(name="nombre")
	private String nombre;
	
	@Column(name="posicion")
	private String posicion;
	
	@Column(name="precio")
	private int precio;
	
	@Column(name="fk_equipo")
	private int fk_equipo;
	
	
	@ManyToOne
	@JoinColumn(name="fk_equipo")
	private Equipo equipo;
	
	@ManyToMany(cascade = {CascadeType.ALL},mappedBy="jugadores")
	private Set<Puntuacion> puntuaciones=new HashSet<>();
	
	@OneToMany(mappedBy="Subasta", cascade=CascadeType.ALL)
	private Set<Subasta> subastas;

	public Jugador() {
		
	}
	
	
	
	
	
	
	

}
