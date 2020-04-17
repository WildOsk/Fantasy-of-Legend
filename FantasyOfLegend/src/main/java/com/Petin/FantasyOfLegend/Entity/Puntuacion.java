package com.Petin.FantasyOfLegend.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Puntuacion")
public class Puntuacion {
	
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

}
