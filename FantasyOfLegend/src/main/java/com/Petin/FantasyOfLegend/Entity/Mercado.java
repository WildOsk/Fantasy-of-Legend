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
@Table(name="Mercado")
public class Mercado implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="id")
	private int id;
	
	@Column(name="fk_liga")
	private int fk_liga;
	
	@ManyToOne
	@JoinColumn(name="fk_liga")
	private Liga liga;
	
	@OneToMany(mappedBy="Subasta", cascade=CascadeType.ALL)
	private Set<Subasta> subastas;
	
	


}
