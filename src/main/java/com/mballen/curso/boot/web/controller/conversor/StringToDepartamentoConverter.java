package com.mballen.curso.boot.web.controller.conversor;
 // essa classe sempre sera chamada sempre q a cargo controller for chamadA
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.mballen.curso.boot.domain.Departamento;
import com.mballen.curso.boot.service.DepartamentoService;
@Component // FAZ COM QUE ESTA CLASSE SEJA GERENCIADA PELO SPRING
public class StringToDepartamentoConverter implements Converter<String,Departamento> {
	@Autowired
private DepartamentoService service;
	@Override
	public Departamento convert(String text) {
		if(text.isEmpty()) {
			return null;	
		}
		Long id= Long.valueOf(text);
		return service.buscarPorId(id) ;
	}

}
