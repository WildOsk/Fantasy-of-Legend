package com.Petin.FantasyOfLegend.Entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="Liga")
public class Liga implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	@Column(name="nombre")
	private String nombre;
	@Column(name="descripcion")
	private String descripcion;
	@Column(name="codigo")
	private String codigo;
	
	
	@OneToOne(cascade=CascadeType.ALL)
	@PrimaryKeyJoinColumn
	private Mercado mercado;
	
	@OneToMany(mappedBy="liga", cascade=CascadeType.ALL)
	private Set<Usuario> usuarios;
	
	public Liga() {
	}

	public Liga(int id, String nombre, String descripcion, String codigo) {
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.codigo = codigo;
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

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}	
	
	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	private static final long serialVersionUID = 1L;

	@Override
	public String toString() {
		return "Liga [id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", codigo=" + codigo + "]";
	}	
}
