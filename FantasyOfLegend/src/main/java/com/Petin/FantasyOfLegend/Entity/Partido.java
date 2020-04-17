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
	@JoinColumn(name="fk_jornada")
	private Jornada jornada;
	
	@OneToMany(mappedBy="Puntuacion", cascade=CascadeType.ALL)
	private Set<Puntuacion> puntuaciones;
	
	
	
	
	
	
	
	

}
