package com.Petin.FantasyOfLegend.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Subasta")
public class Subasta {
	
	@Id
	@Column(name="id")
	private int id;
	
	@Column(name="dinero_ofrecido")
	private int dinero_ofrecido;
	
	@Column(name="fk_jugador_subastado", insertable = false, updatable = false)
	private int fk_jugador_subastado;
	
	@Column(name="fk_usuario",insertable = false, updatable = false)
	private int fk_usuario;
	
	@Column(name="fk_mercado", insertable = false, updatable = false)
	private int fk_mercado;
	
	@ManyToOne
	@JoinColumn(name="fk_mercado")
	private Mercado mercado;
	
	@ManyToOne
	@JoinColumn(name="fk_usuario")
	private Usuario usuario;
	
	@ManyToOne
	@JoinColumn(name="fk_jugador_subastado")
	private Jugador jugador;

	public int getDinero_ofrecido() {
		return dinero_ofrecido;
	}

	public void setDinero_ofrecido(int dinero_ofrecido) {
		this.dinero_ofrecido = dinero_ofrecido;
	}

	public int getFk_jugador_subastado() {
		return fk_jugador_subastado;
	}

	public void setFk_jugador_subastado(int fk_jugador_subastado) {
		this.fk_jugador_subastado = fk_jugador_subastado;
	}

	public int getFk_usuario() {
		return fk_usuario;
	}

	public void setFk_usuario(int fk_usuario) {
		this.fk_usuario = fk_usuario;
	}

	public int getFk_mercado() {
		return fk_mercado;
	}

	public void setFk_mercado(int fk_mercado) {
		this.fk_mercado = fk_mercado;
	}

	@Override
	public String toString() {
		return "Subasta [dinero_ofrecido=" + dinero_ofrecido + ", fk_jugador_subastado=" + fk_jugador_subastado
				+ ", fk_usuario=" + fk_usuario + "]";
	}

	
	
	
	
	

}
