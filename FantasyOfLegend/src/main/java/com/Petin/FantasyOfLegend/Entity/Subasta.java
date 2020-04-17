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
	
	@Column(name="fk_jugador_subastado")
	private int fk_jugador_subastado;
	
	@Column(name="fk_usuario")
	private int fk_usuario;
	
	@Column(name="fk_mercado")
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

}
