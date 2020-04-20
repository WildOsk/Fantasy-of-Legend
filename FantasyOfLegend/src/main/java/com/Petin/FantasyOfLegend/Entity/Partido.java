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
	private String e_local;
	
	@Column(name="visitante")
	private String visitante;
	
	@Column(name="resultado")
	private int resultado;
	
	@Column(name="fk_jornada")
	private int fk_jornada;
	
	
	@ManyToOne
	@JoinColumn(name="fk_jornada",insertable = false, updatable = false)
	private Jornada jornada;
	
	@OneToMany(mappedBy="partido", cascade=CascadeType.ALL)
	private Set<Puntuacion> puntuaciones;

	public Partido() {
		
	}

	public Partido(int id, String e_local, String visitante, int resultado, int fk_jornada) {
		super();
		this.id = id;
		this.e_local = e_local;
		this.visitante = visitante;
		this.resultado = resultado;
		this.fk_jornada = fk_jornada;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getE_local() {
		return e_local;
	}

	public void setE_local(String e_local) {
		this.e_local = e_local;
	}

	public String getVisitante() {
		return visitante;
	}

	public void setVisitante(String visitante) {
		this.visitante = visitante;
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

	@Override
	public String toString() {
		return "Partido [id=" + id + ", e_local=" + e_local + ", visitante=" + visitante + ", resultado=" + resultado
				+ ", fk_jornada=" + fk_jornada + ", jornada=" + jornada + ", puntuaciones=" + puntuaciones + "]";
	}
	
	
	
	
	
	
	
	
	
	
	
	

}
