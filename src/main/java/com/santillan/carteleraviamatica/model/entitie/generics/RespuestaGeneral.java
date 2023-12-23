package com.santillan.carteleraviamatica.model.entitie.generics;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;

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

}
