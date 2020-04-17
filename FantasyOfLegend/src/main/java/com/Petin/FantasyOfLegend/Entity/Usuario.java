package com.Petin.FantasyOfLegend.Entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Usuario")
public class Usuario implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="id")
	private int id;
	
	@Column(name="nombre")
	private String nombre;
	
	@Column(name="apellido")
	private String apellido;
	
	@Column(name="correo")
	private String correo;
	
	@Column(name="alias")
	private String alias;
	
	@Column(name="contrasena")
	private String contrasena;
	
	@Column(name="logo")
	private String logo;
	
	@Column(name="dinero")
	private int dinero;
	
	@Column(name="suma_puntuacion")
	private int suma_puntuacion;
	
	@Column(name="fk_liga")
	private int fk_liga;
	
	@ManyToOne
	@JoinColumn(name="fk_liga")
	private Liga liga;

}
