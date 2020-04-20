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
import javax.persistence.OneToMany;
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
	
	@Column(name="fk_liga",insertable = false, updatable = false)
	private int fk_liga;
	
	@ManyToOne
	@JoinColumn(name="fk_liga")
	private Liga liga;
	
	@OneToMany(mappedBy="usuario", cascade=CascadeType.ALL)
	private Set<Subasta> subastas;
	
	@ManyToMany(cascade = {CascadeType.ALL})
	 @JoinTable(name="roster_usuario", joinColumns={@JoinColumn(name="fk_usuario")}, inverseJoinColumns={@JoinColumn(name="fk_jugador")})
	 private Set<Jugador> jugadores_roster_usuario=new HashSet();
	
	@ManyToMany(cascade = {CascadeType.ALL})
	 @JoinTable(name="rosterFinal_usuario", joinColumns={@JoinColumn(name="fk_usuario")}, inverseJoinColumns={@JoinColumn(name="fk_jugador")})
	 private Set<Jugador> rosterFinal_usuario=new HashSet();

	public Usuario() {
		
	}

	public Usuario(int id, String nombre, String apellido, String correo, String alias, String contrasena, String logo,
			int dinero, int suma_puntuacion, int fk_liga) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.correo = correo;
		this.alias = alias;
		this.contrasena = contrasena;
		this.logo = logo;
		this.dinero = dinero;
		this.suma_puntuacion = suma_puntuacion;
		this.fk_liga = fk_liga;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", correo=" + correo + ", alias="
				+ alias + ", contrasena=" + contrasena + ", logo=" + logo + ", dinero=" + dinero + ", suma_puntuacion="
				+ suma_puntuacion + ", fk_liga=" + fk_liga + ", liga=" + liga + ", subastas=" + subastas
				+ ", jugadores_roster_usuario=" + jugadores_roster_usuario + ", rosterFinal_usuario="
				+ rosterFinal_usuario + "]";
	}
	
	
	
	
	

}
