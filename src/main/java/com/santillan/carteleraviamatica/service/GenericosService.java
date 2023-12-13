package com.santillan.carteleraviamatica.service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.santillan.carteleraviamatica.model.entitie.generics.RespuestaGeneral;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.santillan.carteleraviamatica.exceptions.ExceptionErrores;
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


	public RespuestaGeneral procesar(JSONObject jsonrequest) throws ExceptionErrores{
		RespuestaGeneral respuesta = null;

		if(jsonrequest.isNull("peticion")) {
			log.error("NO SE ENCONTRO EL TIPO DE PETICION PARA EL PROCESO");
			respuesta = new RespuestaGeneral("400", "No se pudo encontrar el tipo de peticion ingresada");
		}

		PeticionGeneral peticionGeneral = new PeticionGeneral(jsonrequest.getString("peticion"));
		if(jsonrequest.isNull("data")) {
			log.error("NO SE ENCONTRO DATA PARA EL PROCESO");
			respuesta = new RespuestaGeneral("400", "No se encontro la data");
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

		//String respuesta = null;

		try {
			switch (casosPeticion) {
				case "consultar":
				String resultConsulta;
					resultConsulta = consultar(peticionGeneral);
					if (resultConsulta != null){
						respuesta = new RespuestaGeneral("200", "Solictud procesada");
						respuesta.getData().put("data", resultConsulta);
					}else{
						respuesta = new RespuestaGeneral("500", "Hubo un error en la consulta");
					}
					break;
				default:
					respuesta = new RespuestaGeneral("400", "No se reconoce la peticion ingresada");
			}
		} catch (ExceptionErrores e) {
			// TODO Auto-generated catch block
			throw new ExceptionErrores("01");
		}

		return respuesta;
	}

	private String consultar(PeticionGeneral peticion) throws ExceptionErrores{

		String devolver = null;

		Map<String, Object> deserializaData = peticion.getData();
		if(deserializaData.isEmpty()) {
			log.error("DATA VACIA");
			return null;
		}
	
//		String entidad = deserializaData.get("entidad") == null ? null : deserializaData.get("entidad").toString();
		String entidad = deserializaData.get("entidad").toString();
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
