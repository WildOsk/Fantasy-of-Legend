package com.Petin.FantasyOfLegend.Entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Puntuacion")
public class Puntuacion implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id")
	private int id;
	
	@Column(name="puntuacionTotal")
	private int puntuacionTotal;
	
	@Column(name="fk_partido")
	private int fk_partido;
	
	@ManyToOne
	@JoinColumn(name="fk_partido")
	private Partido partido;
	
	 @ManyToMany(cascade = {CascadeType.ALL})
	 @JoinTable(name="puntuacion_jugador", joinColumns={@JoinColumn(name="fk_puntuacion")}, inverseJoinColumns={@JoinColumn(name="fk_jugador")})
	 private Set<Jugador> jugadores=new HashSet();

	public Puntuacion() {
		
	}

	public Puntuacion(int id, int puntuacionTotal, int fk_partido) {
		super();
		this.id = id;
		this.puntuacionTotal = puntuacionTotal;
		this.fk_partido = fk_partido;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPuntuacionTotal() {
		return puntuacionTotal;
	}

	public void setPuntuacionTotal(int puntuacionTotal) {
		this.puntuacionTotal = puntuacionTotal;
	}

	public int getFk_partido() {
		return fk_partido;
	}

	public void setFk_partido(int fk_partido) {
		this.fk_partido = fk_partido;
	}

	public Partido getPartido() {
		return partido;
	}

	public void setPartido(Partido partido) {
		this.partido = partido;
	}

	public Set<Jugador> getJugadores() {
		return jugadores;
	}

	public void setJugadores(Set<Jugador> jugadores) {
		this.jugadores = jugadores;
	}

	@Override
	public String toString() {
		return "Puntuacion [id=" + id + ", puntuacionTotal=" + puntuacionTotal + ", fk_partido=" + fk_partido
				+ ", partido=" + partido + ", jugadores=" + jugadores + "]";
	}
	 
	 
	 
	 
	 

}
