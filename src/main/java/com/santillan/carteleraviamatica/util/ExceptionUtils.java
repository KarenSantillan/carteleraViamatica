package com.santillan.carteleraviamatica.util;

import java.util.HashMap;
import java.util.Map;

public class ExceptionUtils {
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
}
