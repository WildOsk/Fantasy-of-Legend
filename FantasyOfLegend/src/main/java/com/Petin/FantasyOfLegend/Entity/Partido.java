package com.Petin.FantasyOfLegend.Entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="Partido")
public class Partido implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id")
	private int id;
	
	@Column(name="e_local")
	private int e_local;
	
	@Column(name="e_visitante")
	private int e_visitante;
	
	@Column(name="resultado")
	private int resultado;
	
	@Column(name="fk_jornada")
	private int fk_jornada;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="fk_jornada",insertable = false, updatable = false)
	private Jornada jornada;
	
	@JsonIgnore
	@OneToMany(mappedBy="partido", cascade=CascadeType.ALL)
	private Set<Puntuacion> puntuaciones;

	public Partido() {
		
	}

	public Partido(int id, int e_local, int visitante, int resultado, int fk_jornada) {
		super();
		this.id = id;
		this.e_local = e_local;
		this.e_visitante = visitante;
		this.resultado = resultado;
		this.fk_jornada = fk_jornada;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getE_local() {
		return e_local;
	}

	public void setE_local(int e_local) {
		this.e_local = e_local;
	}

	public int getVisitante() {
		return e_visitante;
	}

	public void setVisitante(int visitante) {
		this.e_visitante = visitante;
	}

	public int getResultado() {
		return resultado;
	}

	public void setResultado(int resultado) {
		this.resultado = resultado;
	}

	public int getFk_jornada() {
		return fk_jornada;
	}

	public void setFk_jornada(int fk_jornada) {
		this.fk_jornada = fk_jornada;
	}

	public Jornada getJornada() {
		return jornada;
	}

	public void setJornada(Jornada jornada) {
		this.jornada = jornada;
	}

	public Set<Puntuacion> getPuntuaciones() {
		return puntuaciones;
	}

	public void setPuntuaciones(Set<Puntuacion> puntuaciones) {
		this.puntuaciones = puntuaciones;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	
	
	
	
	
	
	
	
	
	
	

}
