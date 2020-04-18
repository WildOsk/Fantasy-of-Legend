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
	
	

	public Mercado() {
	
	}



	public Mercado(int id, int fk_liga) {
		super();
		this.id = id;
		this.fk_liga = fk_liga;
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public int getFk_liga() {
		return fk_liga;
	}



	public void setFk_liga(int fk_liga) {
		this.fk_liga = fk_liga;
	}



	public Liga getLiga() {
		return liga;
	}



	public void setLiga(Liga liga) {
		this.liga = liga;
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



	@Override
	public String toString() {
		return "Mercado [id=" + id + ", fk_liga=" + fk_liga + ", liga=" + liga + ", subastas=" + subastas + "]";
	}
	
	
	
	


}
