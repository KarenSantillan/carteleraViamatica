package com.santillan.carteleraviamatica.controller;

import com.santillan.carteleraviamatica.exceptions.ExceptionErrores;
import com.santillan.carteleraviamatica.model.entitie.generics.RespuestaGeneral;
import com.santillan.carteleraviamatica.service.GenericosService;
import com.santillan.carteleraviamatica.util.ExceptionUtils;

import jakarta.validation.ValidationException;
import org.apache.commons.io.IOUtils;
import org.everit.json.schema.Schema;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
public class PeticionGeneralController {
	
	@Autowired
	GenericosService genService;


	//CARGAR EL ESQUEMA JSON
	InputStream peticionSchemaStream = getClass().getResourceAsStream("/estructura_json/peticionSchema.json");
	String peticionSchemaJson = null;
	{
		try {
			peticionSchemaJson = IOUtils.toString(peticionSchemaStream, StandardCharsets.UTF_8);
		} catch (IOException e) {
			e.printStackTrace(); // Maneja la excepción
		}
	}

	private Schema peticionRequestSchema = SchemaLoader.load(new JSONObject(peticionSchemaJson));

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@PostMapping(path = "/peticion")
	public ResponseEntity<RespuestaGeneral> procesarPeticion(@RequestBody String datos) {
		//String response = null;
		log.info("ENTRADA DE INFORMACION: " + datos);
		JSONObject jsonReq = new JSONObject(datos);
		
		//validar con JSONSchema
		try {
			peticionRequestSchema.validate(jsonReq);
		} catch (ValidationException e) {
			RespuestaGeneral rsp = new RespuestaGeneral("400", "Error en la validacion del esquema");
			HttpStatus sta = HttpStatus.OK;
			if(!"200".equals(rsp.getCodigo())){
				sta = HttpStatus.EXPECTATION_FAILED;
			}
			return new ResponseEntity<>(rsp, sta);
		}

		RespuestaGeneral respuesta;
		try {
			respuesta = genService.procesar(jsonReq);
			if (respuesta == null){
				respuesta = new RespuestaGeneral("500", "Se ha generado un error en el servicio");
			}
			HttpStatus httpSta = HttpStatus.OK;
			if(!"200".equals(respuesta.getCodigo())){
				httpSta = HttpStatus.INTERNAL_SERVER_ERROR;
				
			}
			return new ResponseEntity<>(respuesta, httpSta);
		} catch (ExceptionErrores e) {
			// TODO Auto-generated catch block
			respuesta = new RespuestaGeneral("01", "NO SE HA PODIDO REALIZAR EL PROCESO", ExceptionUtils.manejoError(e.getCodError()));
			
			return new ResponseEntity(respuesta, HttpStatusCode.valueOf(500));
		}


		/*Response = genService.procesar(jsonReq);
		if(response == null) {
			response = "SIN RESPUESTA DEL SERVICIO";
		}

		return response;*/
	}

}
