package com.santillan.carteleraviamatica.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.santillan.carteleraviamatica.service.GenericosService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class PeticionGeneralController {
	
	@Autowired
	GenericosService genService;
	
	@PostMapping(path = "/peticion")
	public String procesarPeticion(@RequestBody String datos) {
		
		String response = null;
		
		log.info("ENTRADA DE INFORMACION: " + datos);
		JSONObject jsonReq = new JSONObject(datos);
		//validar con JSONSchema
		//
		//
		
		response = genService.procesar(jsonReq);
		if(response == null) {
			response = "SIN RESPUESTA DEL SERVICIO";
		}
		
		return response;
	}
}
