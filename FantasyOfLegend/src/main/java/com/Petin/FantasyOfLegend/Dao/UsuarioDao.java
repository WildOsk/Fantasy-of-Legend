package com.Petin.FantasyOfLegend.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.Petin.FantasyOfLegend.Entity.Usuario;


public interface UsuarioDao extends JpaRepository<Usuario, Integer>{	
}
