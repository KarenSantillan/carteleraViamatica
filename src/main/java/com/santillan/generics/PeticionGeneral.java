package com.santillan.carteleraviamatica.model.entitie.generics;

import java.util.HashMap;
import java.util.Map;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PeticionGeneral {

	private String peticion;
	private Map<String, Object> data;
	
	public PeticionGeneral(String peticion) {
		this.peticion = peticion;
		data = new HashMap<String, Object>();
	}
}
