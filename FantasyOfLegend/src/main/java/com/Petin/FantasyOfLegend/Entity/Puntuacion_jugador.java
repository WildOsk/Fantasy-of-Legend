package com.Petin.FantasyOfLegend.Entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="puntuacion_jugador")
public class Puntuacion_jugador implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id")
	private int id;
	
	@Column(name="puntuacion")
	private int puntuacion;
	
	@ManyToOne
	@JoinColumn(name="fk_jugador", insertable = false, updatable = false)
	private Jugador jugador;
	
	@ManyToOne
	@JoinColumn(name="fk_partido", insertable = false, updatable = false)
	private Partido partido;
	

	public Puntuacion_jugador(){
	}


	public Puntuacion_jugador(int id, int puntuacion, Jugador jugador, Partido partido) {
		super();
		this.id = id;
		this.puntuacion = puntuacion;
		this.jugador = jugador;
		this.partido = partido;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getPuntuacion() {
		return puntuacion;
	}


	public void setPuntuacion(int puntuacion) {
		this.puntuacion = puntuacion;
	}


	public Jugador getJugador() {
		return jugador;
	}


	public void setJugador(Jugador jugador) {
		this.jugador = jugador;
	}


	public Partido getPartido() {
		return partido;
	}


	public void setPartido(Partido partido) {
		this.partido = partido;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	


}
