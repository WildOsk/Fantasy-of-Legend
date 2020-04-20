package com.Petin.FantasyOfLegend.Entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
	
	@ManyToOne
	@JoinColumn(name="fk_equipo", insertable = false, updatable = false)
	private Equipo equipo;
	
	@JsonIgnore
	@ManyToMany(cascade = {CascadeType.ALL},mappedBy="jugadores")
	private Set<Puntuacion> puntuaciones=new HashSet<>();
	
	@JsonIgnore
	@ManyToMany(cascade = {CascadeType.ALL},mappedBy="jugadores_roster_usuario")
	private Set<Usuario> jugadores_roster_usuario=new HashSet<>();
	
	@JsonIgnore
	@ManyToMany(cascade = {CascadeType.ALL},mappedBy="rosterFinal_usuario")
	private Set<Usuario> rosterFinal_usuario=new HashSet<>();
	
	@JsonIgnore
	@OneToMany(mappedBy="fk_jugador_subastado", cascade=CascadeType.ALL)
	private Set<Subasta> subastas;

	public Jugador() {
		
	}
	
	

	public Jugador(int id, String nombre, String posicion, int precio, Equipo equipo) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.posicion = posicion;
		this.precio = precio;
		this.equipo = equipo;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPosicion() {
		return posicion;
	}

	public void setPosicion(String posicion) {
		this.posicion = posicion;
	}

	public int getPrecio() {
		return precio;
	}

	public void setPrecio(int precio) {
		this.precio = precio;
	}

	public Equipo getFk_equipo() {
		return equipo;
	}

	public void setFk_equipo(Equipo equipo) {
		this.equipo = equipo;
	}

	public Equipo getEquipo() {
		return equipo;
	}

	public void setEquipo(Equipo equipo) {
		this.equipo = equipo;
	}

	public Set<Puntuacion> getPuntuaciones() {
		return puntuaciones;
	}

	public void setPuntuaciones(Set<Puntuacion> puntuaciones) {
		this.puntuaciones = puntuaciones;
	}

	public Set<Subasta> getSubastas() {
		return subastas;
	}

	public void setSubastas(Set<Subasta> subastas) {
		this.subastas = subastas;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	public Set<Usuario> getJugadores_roster_usuario() {
		return jugadores_roster_usuario;
	}



	public void setJugadores_roster_usuario(Set<Usuario> jugadores_roster_usuario) {
		this.jugadores_roster_usuario = jugadores_roster_usuario;
	}



	public Set<Usuario> getRosterFinal_usuario() {
		return rosterFinal_usuario;
	}



	public void setRosterFinal_usuario(Set<Usuario> rosterFinal_usuario) {
		this.rosterFinal_usuario = rosterFinal_usuario;
	}
	
	
	
	
	
	
	
	
	

}
