package com.Petin.FantasyOfLegend.Dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Petin.FantasyOfLegend.Entity.Jugador;
import com.Petin.FantasyOfLegend.Entity.Liga;


public interface JugadoresDao extends JpaRepository<Jugador, Integer>{

}
