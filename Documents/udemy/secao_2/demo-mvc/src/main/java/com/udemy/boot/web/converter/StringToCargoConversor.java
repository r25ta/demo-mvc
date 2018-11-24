package com.udemy.boot.web.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.udemy.boot.web.domain.Cargo;
import com.udemy.boot.web.service.CargoService;

@Component
public class StringToCargoConversor implements Converter<String, Cargo> {

	@Autowired
	CargoService cargoService;
	
	public Cargo convert(String text) {
		
		if(text.isEmpty()) {
			return null;
		}
		Long id = Long.valueOf(text);
		return cargoService.buscarPorId(id); 
	}
}
