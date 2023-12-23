package com.santillan.carteleraviamatica.util;

import java.util.HashMap;
import java.util.Map;

public class ExceptionUtils {
<<<<<<< HEAD
    static Map<String, Object> mapError;
    public static Map<String, Object> manejoError(String codError){
       mapError = new HashMap<String, Object>();
        switch(codError) {
            //envio de informacion incorrecto
            case "01":
                mapError.put("msjTecnico", "Ha ocurrido un error al enviar los parametros");
            break;
            //mapeo de informacion o conversion de entidad incorrecto
            case "02":
                mapError.put("mensaje", "Ha ocurrido un error al convertir o mapear la entidad");
                break;
            //error en proceso, valores nulos, valores no casteados (convertidos)
            case "03":
                mapError.put("mensaje", "Ha ocurrido un error en la transacción o el proceso");
                break;
            //errores internos del servidor,conexiones, llamadas, respuesta de servicio erroneas
            default:
                mapError.put("mensaje", "Ha ocurrido un error interno del proveedor");
                break;
        }
        return mapError;
    }
=======

	static Map<String, Object> mapError;
	
	public static Map<String, Object> manejoError(String codError) {
		mapError = new HashMap<String, Object>();
		switch(codError) {
		//mal envio de informacion
			case "01":
				mapError.put("msjTecnico", "El error ocurrio al enviar los parametros");
			break;
		//mal mapeo de informacion o conversion de entidad
			case "02":
				mapError.put("mensaje", "Error al convertir o mapear la entidad");
				break;
		//errores en proceso, valores nulos, o valores no casteados (convertidos)
			case "03":
				mapError.put("mensaje", "Ocurrio un error en la transaccion o el proceso");
				break;
		//errores del servidor interno, conexiones o llamadas o respuestas de servicio erroneas.
			default:
				mapError.put("mensaje", "error interno del proveedor");
				break;
				
		}
		
		return mapError;
	}
>>>>>>> 8c0a7d232989c87d9398ab543845371e8b55cec6
}
