package com.santillan.carteleraviamatica.model.entitie.generics;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data
@NoArgsConstructor
public class RespuestaGeneral {
    private String codigo;
    private String mensaje;
    private HashMap<String, Object> data;

    public RespuestaGeneral(String codigo, String mensaje){
            this.codigo = codigo;
            this.mensaje = mensaje;
            data = new HashMap<String, Object>();
    }
    public RespuestaGeneral(String codigo, String mensaje, Map<String, Object> mapper){
        this.codigo = codigo;
        this.mensaje = mensaje;
        data = (HashMap<String, Object>) mapper;
    }
}
