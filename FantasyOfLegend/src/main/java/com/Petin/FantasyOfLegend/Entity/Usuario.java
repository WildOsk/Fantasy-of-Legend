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

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="Usuario")
public class Usuario implements Serializable {

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
	
	@Column(name="rol")
	private String rol;
	
	@Column(name="dinero")
	private int dinero;
	
	@JsonIgnore
	@Column(name="suma_puntuacion")
	private int suma_puntuacion;
	
	@JsonIgnore
	@Column(name="fk_liga",insertable = false, updatable = false)
	private int fk_liga;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="fk_liga")
	private Liga liga;
	
	@JsonIgnore
	@OneToMany(mappedBy="usuario", cascade=CascadeType.ALL)
	private Set<Subasta> subastas;
	
	@JsonIgnore
	@ManyToMany(cascade = {CascadeType.ALL})
	 @JoinTable(name="roster_usuario", joinColumns={@JoinColumn(name="fk_usuario")}, inverseJoinColumns={@JoinColumn(name="fk_jugador")})
	 private Set<Jugador> jugadores_roster_usuario=new HashSet();
	
	@JsonIgnore
	@ManyToMany(cascade = {CascadeType.ALL})
	 @JoinTable(name="rosterFinal_usuario", joinColumns={@JoinColumn(name="fk_usuario")}, inverseJoinColumns={@JoinColumn(name="fk_jugador")})
	 private Set<Jugador> rosterFinal_usuario=new HashSet();

	public Usuario() {
		
	}

	public Usuario(int id, String nombre, String apellido, String correo, String alias, String contrasena, String logo,
			String rol, int dinero, int suma_puntuacion, int fk_liga) {
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.correo = correo;
		this.alias = alias;
		this.contrasena = contrasena;
		this.logo = logo;
		this.rol = rol;
		this.dinero = dinero;
		this.suma_puntuacion = suma_puntuacion;
		this.fk_liga = fk_liga;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	public int getDinero() {
		return dinero;
	}

	public void setDinero(int dinero) {
		this.dinero = dinero;
	}

	public int getSuma_puntuacion() {
		return suma_puntuacion;
	}

	public void setSuma_puntuacion(int suma_puntuacion) {
		this.suma_puntuacion = suma_puntuacion;
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

	public Set<Jugador> getJugadores_roster_usuario() {
		return jugadores_roster_usuario;
	}

	public void setJugadores_roster_usuario(Set<Jugador> jugadores_roster_usuario) {
		this.jugadores_roster_usuario = jugadores_roster_usuario;
	}

	public Set<Jugador> getRosterFinal_usuario() {
		return rosterFinal_usuario;
	}

	public void setRosterFinal_usuario(Set<Jugador> rosterFinal_usuario) {
		this.rosterFinal_usuario = rosterFinal_usuario;
	}
	
	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", correo=" + correo + ", alias="
				+ alias + ", contrasena=" + contrasena + "]";
	}
}
