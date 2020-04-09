package com.mballen.curso.boot.dao;

import org.springframework.stereotype.Repository;

import com.mballen.curso.boot.domain.Cargo;

@Repository  // transações sql
public class CargoDaoImpl extends AbstractDao<Cargo,Long> implements CargoDao {
  
}
