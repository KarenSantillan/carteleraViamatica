package com.santillan.carteleraviamatica.service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.santillan.carteleraviamatica.model.entitie.Pelicula;
import com.santillan.carteleraviamatica.model.entitie.PeliculaSalacine;
import com.santillan.carteleraviamatica.model.entitie.SalaCine;
import com.santillan.carteleraviamatica.model.entitie.generics.PeticionGeneral;
import com.santillan.carteleraviamatica.repository.PeliculaRepository;
import com.santillan.carteleraviamatica.repository.PeliculaSalaCineRepository;
import com.santillan.carteleraviamatica.repository.SalaCineRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class GenericosService {

	@Autowired
	PeliculaRepository peliculaRepository;
	
	@Autowired
	SalaCineRepository salacineRepository;
	
//	@Autowired
//	PeliculaSalaCineRepository peliculasalacineRepository;
	
	
	public String procesar(JSONObject jsonrequest) {
		if(jsonrequest.isNull("peticion")) {
			log.error("NO SE ENCONTRO EL TIPO DE PETICION PARA EL PROCESO");
			return null;
		}
		
		PeticionGeneral peticionGeneral = new PeticionGeneral(jsonrequest.getString("peticion"));
		if(jsonrequest.isNull("data")) {
			log.error("NO SE ENCONTRO DATA PARA EL PROCESO");
			return null;
		}
		
		Iterator<?> iterar = jsonrequest.getJSONObject("data").keys();
		Map<String, Object> mapperAux = new HashMap<String, Object>();
		while(iterar.hasNext()) {
			String key = iterar.next().toString();
			Object value = jsonrequest.getJSONObject("data").get(key);
			mapperAux.put(key, value);
		}
		peticionGeneral.setData(mapperAux);
		
		
		String casosPeticion = jsonrequest.getString("peticion");
		
		String respuesta = null;
		
		switch (casosPeticion) {
			case "consultar":
				respuesta = consultar(peticionGeneral);
				break;
				
		}
		
		return respuesta;
	}
	
	private String consultar(PeticionGeneral peticion) {
		
		String devolver = null;
		
		Map<String, Object> deserializaData = peticion.getData();
		if(deserializaData.isEmpty()) {
			log.error("DATA VACIA");
			return null;
		}
		String entidad = deserializaData.get("entidad") == null ? null : deserializaData.get("entidad").toString();
		if(StringUtils.isNotEmpty(entidad)) {
			switch (entidad) {
			case "Pelicula":
				List<Pelicula> lPeliculas = peliculaRepository.findAll();
				System.out.println(lPeliculas);
				devolver = lPeliculas.toString();
				break;
//				
//			case "PeliculaSalaCine":
//				List<PeliculaSalacine> lpeliculasSalaCine = peliculasalacineRepository.findAll();
//				devolver = lpeliculasSalaCine.toString();
//				break;
				
			case "SalaCine":
				List<SalaCine> lSalaCines = salacineRepository.findAll();
				devolver = lSalaCines.toString();

				break;
				
			}
		}
		return devolver;
	}
}
