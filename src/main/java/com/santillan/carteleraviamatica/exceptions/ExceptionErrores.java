package com.santillan.carteleraviamatica.exceptions;

import com.santillan.carteleraviamatica.util.ExceptionUtils;

public class ExceptionErrores extends Exception{
    private static final long serialVersionUID = 1L;

    String codError;
    public String getCodError(){
        return codError;
    }

    public ExceptionErrores(String codError, String mensajeError){
        super(mensajeError);
        this.codError = codError;
    }

    public ExceptionErrores(String codError){
        ExceptionUtils.manejoError(codError);
    }













}
