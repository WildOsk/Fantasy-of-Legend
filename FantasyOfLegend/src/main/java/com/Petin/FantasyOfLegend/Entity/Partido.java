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
	
	@Column(name="resultado")
	private String resultado;
	
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
	
	public Partido(int id, String resultado) {
		super();
		this.id = id;
		this.resultado = resultado;
	}

	public Partido(int id, String resultado, int fk_jornada) {
		super();
		this.id = id;
		this.resultado = resultado;
		this.fk_jornada = fk_jornada;
	}
	
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public String getResultado() {
		return resultado;
	}

	public void setResultado(String resultado) {
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
